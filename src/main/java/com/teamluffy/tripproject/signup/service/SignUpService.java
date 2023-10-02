package com.teamluffy.tripproject.signup.service;

import com.teamluffy.tripproject.component.MailComponent;
import com.teamluffy.tripproject.exception.CustomException;
import com.teamluffy.tripproject.exception.ErrorCode;
import com.teamluffy.tripproject.signup.domain.SignUpForm;
import com.teamluffy.tripproject.signup.domain.entity.User;
import com.teamluffy.tripproject.signup.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

import static com.teamluffy.tripproject.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final UserRepository userRepository;
    private final MailComponent mailComponent;

    public String signUp(SignUpForm signUpForm){

        if(this.isEmailExist(signUpForm.getEmail())){
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USE);
        }

        String uuid = UUID.randomUUID().toString();
        signUpForm.setVerificationCode(uuid);

        userRepository.save(User.from(signUpForm));
        LocalDateTime now = LocalDateTime.now();

        //이메일 인증
        String email = signUpForm.getEmail();
        String subject = "회원가입을 축하드립니다.";
        String text = "<p>회원가입을 축하드립니다.</p><p>아래 링크를 클릭해 가입을 완료하세요.</p>"
                +"<div><a href='http://localhost:8081/signup/verify?id="+uuid+"'>가입 완료 </a></div>";

        mailComponent.sendMail(email,subject,text);

        return "";
    }

    public boolean isEmailExist(String email) {
        return userRepository.findByEmail(email.toLowerCase(Locale.ROOT)).isPresent();
    }

    @Transactional
    public void verifyEmail(String uuid){
        User user = userRepository.findByVerificationCode(uuid)
                .orElseThrow(() -> new CustomException(NOT_REGISTER_USER));

        //이미 증명완료
        if(user.isVerify()){
            throw new CustomException(ALREADY_VERIFY);
        }

        user.setVerify(true);
    }

}
