package com.teamluffy.tripproject.signup.controller;

import com.teamluffy.tripproject.signup.domain.SignUpForm;
import com.teamluffy.tripproject.signup.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignUpController {
    private final SignUpService signUpService;

    @GetMapping
    public String signUp(Model model){
        model.addAttribute("signUpForm",new SignUpForm());
        return "signup/register";
    }

    @PostMapping
    public String signUpSubmit(@ModelAttribute @Valid SignUpForm signUpForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "signup/register";
        }

        signUpService.signUp(signUpForm);
        return "signup/register";
    }

    @GetMapping(value = "/verify")
    public String signUpVerify(@RequestParam(value = "id")String uuid){
        signUpService.verifyEmail(uuid);

        return "index";
    }

}
