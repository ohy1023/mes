package com.example.mes.user.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.mes.common.exception.MesAppException;
import com.example.mes.user.dto.*;
import com.example.mes.user.entity.User;
import com.example.mes.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static com.example.mes.common.exception.ErrorCode.*;
import static com.example.mes.user.enums.UserRole.ADMIN;
import static com.example.mes.user.enums.UserRole.USER;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Transactional
    public UserJoinResponse join(UserJoinRequest userJoinRequest) {
        userRepository.findByEmail(userJoinRequest.getEmail())
                .ifPresent((user -> {
                    throw new MesAppException(DUPLICATED_EMAIL, DUPLICATED_EMAIL.getMessage());
                }));
        User savedUser = userRepository.save(userJoinRequest.toEntity(encoder.encode(userJoinRequest.getPassword())));

        return UserJoinResponse.toResponse(savedUser);
    }

    @Transactional
    public UserRoleResponse changeRole(Long userId, String email) {
        User admin = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new MesAppException(EMAIL_NOT_FOUND, EMAIL_NOT_FOUND.getMessage());
                });

        User targetUser = userRepository.findById(userId).orElseThrow(() -> {
            throw new MesAppException(EMAIL_NOT_FOUND, EMAIL_NOT_FOUND.getMessage());
        });

        if (admin.getUserRole() != ADMIN) {
            throw new MesAppException(INVALID_PERMISSION, INVALID_PERMISSION.getMessage());
        }

        if (targetUser.getUserRole() == USER) targetUser.promoteRole(targetUser);
        else if (targetUser.getUserRole() == ADMIN) targetUser.demoteRole(targetUser);

        return UserRoleResponse.toResponse(targetUser);
    }

    @Transactional(readOnly = true)
    public UserGetResponse getInfo(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new MesAppException(EMAIL_NOT_FOUND, EMAIL_NOT_FOUND.getMessage());
                });

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        String date = user.getCreatedAt().format(formatter);
        return UserGetResponse.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .imageUrl(user.getImageUrl())
                .createAt(date)
                .build();

    }

    @Transactional
    public boolean validate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new MesAppException(EMAIL_NOT_FOUND, EMAIL_NOT_FOUND.getMessage());
                });

        if (isWrongPassword(password, user))
            throw new MesAppException(INVALID_PASSWORD, INVALID_PASSWORD.getMessage());

        return true;
    }

    @Transactional
    public Long delete(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new MesAppException(EMAIL_NOT_FOUND, EMAIL_NOT_FOUND.getMessage());
                });

        userRepository.deleteById(user.getId());

        return user.getId();
    }

    @Transactional
    public String getTempPassword(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new MesAppException(EMAIL_NOT_FOUND, EMAIL_NOT_FOUND.getMessage());
                });

        String tempKey = "CoinOne" + UUID.randomUUID();

        String tempPassword = encoder.encode(tempKey);

        user.updatePassword(tempPassword);

        return tempKey;
    }


    @Transactional
    public Long modifyPassword(String email, UserUpdatePasswordRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new MesAppException(EMAIL_NOT_FOUND, EMAIL_NOT_FOUND.getMessage());
                });

        if (isWrongPassword(request.getPassword(), user))
            throw new MesAppException(INVALID_PASSWORD, INVALID_PASSWORD.getMessage());

        if (!request.getNewPassword().equals(request.getReNewPassword())) {
            throw new MesAppException(INVALID_PASSWORD, INVALID_PASSWORD.getMessage());
        }

        user.updatePassword(encoder.encode(request.getNewPassword()));

        return user.getId();
    }

    @Transactional
    public UserPutResponse modify(MultipartFile multipartFile, String userName, String email, int removeClick) throws IOException {
        if (multipartFile == null) {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> {
                        throw new MesAppException(EMAIL_NOT_FOUND, EMAIL_NOT_FOUND.getMessage());
                    });

            if (removeClick == 1) {
                user.updateUser(userName, "h");
            } else {
                user.updateUserName(userName);
            }

            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
            String date = user.getCreatedAt().format(formatter);
            return UserPutResponse.builder()
                    .userName(user.getUserName())
                    .email(user.getEmail())
                    .imageUrl(user.getImageUrl())
                    .createAt(date)
                    .build();
        } else {
            String fileName = multipartFile.getOriginalFilename();
            log.info("fileName:{}", fileName);
            //파일 형식 구하기
            String ext = fileName.split("\\.")[1];
            String contentType = "";

            //content type을 지정해서 올려주지 않으면 자동으로 "application/octet-stream"으로 고정이 되서 링크 클릭시 웹에서 열리는게 아니라 자동 다운이 시작됨.
            switch (ext) {
                case "jpeg":
                    contentType = "image/jpeg";
                    break;
                case "png":
                    contentType = "image/png";
                    break;
                case "jpg":
                    contentType = "image/jpg";
                    break;
            }
            try {
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentType(contentType);

                amazonS3.putObject(new PutObjectRequest(bucket, fileName, multipartFile.getInputStream(), metadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            } catch (AmazonServiceException e) {
                e.printStackTrace();
            } catch (SdkClientException e) {
                e.printStackTrace();
            }

            String imageUrl = amazonS3.getUrl(bucket, fileName).toString();


            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> {
                        throw new MesAppException(EMAIL_NOT_FOUND, EMAIL_NOT_FOUND.getMessage());
                    });

            user.updateUser(userName, imageUrl);

            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
            String date = user.getCreatedAt().format(formatter);
            return UserPutResponse.builder()
                    .userName(user.getUserName())
                    .email(user.getEmail())
                    .imageUrl(user.getImageUrl())
                    .createAt(date)
                    .build();
        }
    }

    private boolean isWrongPassword(String password, User user) {
        return !encoder.matches(password, user.getPassword());
    }

}
