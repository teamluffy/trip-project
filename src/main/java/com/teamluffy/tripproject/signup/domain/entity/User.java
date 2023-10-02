package com.teamluffy.tripproject.signup.domain.entity;

import com.teamluffy.tripproject.signup.domain.LoginType;
import com.teamluffy.tripproject.signup.domain.Roles;
import com.teamluffy.tripproject.signup.domain.SignUpForm;
import lombok.*;
import org.hibernate.envers.AuditOverride;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Locale;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@AuditOverride(forClass = BaseEntity.class)
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String nickname;
    private boolean withdraw;
    private LocalDateTime withdrawAt;

    // admin, user
    @Enumerated(EnumType.STRING)
    private Roles roles;

    // naver, kakao, home
    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    private String verificationCode;
    private boolean verify;


    // 20231002 이강욱 : loginType 받는 메서드 추가되면 수정 필요
    public static User from(SignUpForm form){
        return User.builder()
                .email(form.getEmail().toLowerCase(Locale.ROOT))
                .password(form.getPassword())
                .nickname(form.getNickname())
                .verificationCode(form.getVerificationCode())
                .withdraw(false)
                .roles(Roles.user)
                .loginType(LoginType.home)
                .verify(false)
                .build();
    }

}
