package com.example.mes.user.entity;

import com.example.mes.common.entity.BaseEntity;
import com.example.mes.user.enums.SocialType;
import com.example.mes.user.enums.UserRole;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import static com.example.mes.user.enums.UserRole.ADMIN;
import static com.example.mes.user.enums.UserRole.USER;


@Entity
@Getter
@Table(name = "USERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deleted_at IS NULL")
@SQLDelete(sql = "UPDATE USERS SET deleted_at = CURRENT_TIMESTAMP WHERE user_id = ?")
@SequenceGenerator(
        name = "USER_SEQ_GENERATOR",
        sequenceName = "USER_SEQ", //매핑할 데이터베이스 시퀀스 이름
        initialValue = 1, allocationSize = 1)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GENERATOR")
    @Column(name = "user_id")
    private Integer id;

    private String userName;

    private String password;

    private String email;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    private SocialType socialType; // KAKAO, NAVER, GOOGLE

    private String socialId; // 로그인한 소셜 타입의 식별자 값 (일반 로그인인 경우 null)


    @PrePersist
    public void prePersist() {
        this.userRole = this.userRole == null ? USER : this.userRole;
        this.imageUrl = this.imageUrl == null ? "https://ohy1023.s3.ap-northeast-2.amazonaws.com/basic.png" : this.imageUrl;
    }

    public User promoteRole(User user) {
        user.userRole = ADMIN;
        return user;
    }

    public User demoteRole(User user) {
        user.userRole = USER;
        return user;
    }

    public void updateUser(String userName, String imageUrl) {
        this.userName = userName;
        this.imageUrl = imageUrl;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    public void updateUserName(String userName) {
        this.userName = userName;
    }

    @Builder
    public User(Integer id, String userName, String password, String email, String imageUrl, UserRole userRole, SocialType socialType, String socialId) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.imageUrl = imageUrl;
        this.userRole = userRole;
        this.socialType = socialType;
        this.socialId = socialId;
    }
}
