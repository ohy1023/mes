package com.example.mes.domain.user;

import com.example.mes.common.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;


@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deleted_at IS NULL")
@SQLDelete(sql = "UPDATE USERS SET deleted_at = CURRENT_TIMESTAMP WHERE user_id = ?")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String userName;

    private String password;

    private String email;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @PrePersist
    public void prePersist() {
        this.userRole = this.userRole == null ? UserRole.USER : this.userRole;
        this.imageUrl = this.imageUrl == null ? "https://ficket-event-content.s3.ap-northeast-2.amazonaws.com/mutsa-sns/basic_profile.png" : this.imageUrl;
    }

    public User promoteRole(User user) {
        user.userRole = UserRole.ADMIN;
        return user;
    }

    public User demoteRole(User user) {
        user.userRole = UserRole.USER;
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
    public User(Long id, String userName, String password, String email, String imageUrl, UserRole userRole) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.imageUrl = imageUrl;
        this.userRole = userRole;
    }
}
