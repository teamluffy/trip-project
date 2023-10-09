package com.teamluffy.tripproject.signin.controller;

import com.teamluffy.tripproject.signin.dto.UserDto;
import com.teamluffy.tripproject.signin.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserLoginService userLoginService;

    @GetMapping("/user/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/user/save")
    public String save(@ModelAttribute UserDto userDTO) {
        System.out.println("UserController.save");
        System.out.println("userDTO : " + userDTO);
        userLoginService.save(userDTO);

        return "index";
    }

    @GetMapping("/user/login")
    public String loginForm() {
        return "login";
    }

    // session 로그인 유지
    @PostMapping("/user/login")
    public String login(@ModelAttribute UserDto userDTO, HttpSession session) {
        UserDto loginResult = userLoginService.login(userDTO);
        if (loginResult != null) {
            session.setAttribute("loginEmail", loginResult.getUserEmail());
            return "main";
        } else {
            return "login";
        }
    }
}
