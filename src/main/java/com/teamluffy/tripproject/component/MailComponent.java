package com.teamluffy.tripproject.component;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
@RequiredArgsConstructor
public class MailComponent {
    private final JavaMailSender javaMailSender;

    public void sendMail(String email, String subject, String text){

        MimeMessagePreparator msg = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {

                MimeMessageHelper msg = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                msg.setTo(email);
                msg.setSubject(subject);
                msg.setText(text,true);
            }
        };

        javaMailSender.send(msg);
    }
}
