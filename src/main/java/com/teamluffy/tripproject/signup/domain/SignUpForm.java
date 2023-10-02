package com.teamluffy.tripproject.signup.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SignUpForm {
    @NotEmpty(message="이메일은 필수입니다.")
    private String email;
    @NotEmpty(message="비밀번호는 필수입니다.")
    private String password;
    private String nickname;
    private String verificationCode;
}
