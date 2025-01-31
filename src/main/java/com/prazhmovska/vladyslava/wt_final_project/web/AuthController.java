package com.prazhmovska.vladyslava.wt_final_project.web;

import com.prazhmovska.vladyslava.wt_final_project.model.User;
import com.prazhmovska.vladyslava.wt_final_project.model.dto.LogInDto;
import com.prazhmovska.vladyslava.wt_final_project.model.dto.TokenDto;
import com.prazhmovska.vladyslava.wt_final_project.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;
    @PostMapping("/sign-up")
    public void signUp (@RequestBody User user) {
    authService.signUp(user);
    }

    @PostMapping("/log-in")
    public TokenDto logIn (@RequestBody LogInDto logInDto){
    return authService.logIn(logInDto.getEmail(), logInDto.getPassword());

    }

}
