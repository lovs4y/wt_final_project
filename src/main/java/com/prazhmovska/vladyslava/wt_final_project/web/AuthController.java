package com.prazhmovska.vladyslava.wt_final_project.web;

import com.prazhmovska.vladyslava.wt_final_project.model.User;
import com.prazhmovska.vladyslava.wt_final_project.model.dto.LogInDto;
import com.prazhmovska.vladyslava.wt_final_project.model.dto.TokenDto;
import com.prazhmovska.vladyslava.wt_final_project.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling user authentication-related operations such as sign-up and log-in.
 *
 * <p>This class provides endpoints for users to sign up and log in. It delegates the business logic to the
 * {@link AuthService} and handles HTTP requests to the appropriate endpoints.</p>
 */
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    /**
     * Handles the user sign-up process by delegating to the {@link AuthService}.
     *
     * <p>Accepts a {@link User} object from the request body, validates it, and then registers the user via the
     * {@link AuthService}.</p>
     *
     * @param user The {@link User} object containing the user's sign-up information.
     */
    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user) {
        authService.signUp(user);
    }

    /**
     * Handles the user log-in process by delegating to the {@link AuthService}.
     *
     * <p>Accepts a {@link LogInDto} object containing the user's email and password,
     * and returns a {@link TokenDto} containing the generated JWT token.</p>
     *
     * @param logInDto The {@link LogInDto} object containing the user's email and password.
     * @return A {@link TokenDto} containing the JWT token for the authenticated user.
     */
    @PostMapping("/log-in")
    public TokenDto logIn(@RequestBody LogInDto logInDto) {
        return authService.logIn(logInDto.getEmail(), logInDto.getPassword());
    }
}