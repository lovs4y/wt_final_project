package com.prazhmovska.vladyslava.wt_final_project.service;

import com.prazhmovska.vladyslava.wt_final_project.core.exceptions.NotFoundException;
import com.prazhmovska.vladyslava.wt_final_project.core.exceptions.ValidationException;
import com.prazhmovska.vladyslava.wt_final_project.model.User;
import com.prazhmovska.vladyslava.wt_final_project.model.dto.TokenDto;
import com.prazhmovska.vladyslava.wt_final_project.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Authentication class that signs up/logs in a user and checks
 * if the password and email formats are correct.
 */
@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Registers a new user by validating their email and password, encoding their password,
     * and saving the user to the repository.
     *
     * <p>Validates the user's email format to ensure it is a valid email address and checks that
     * the password is at least 6 characters Long. If either of these conditions is not met,
     * a {@link ValidationException} is thrown. Once the validations pass, the password is encoded
     * using the provided {@link PasswordEncoder} and the user is saved to the repository.</p>
     *
     * @param user The user to be registered.
     * @throws ValidationException if password or email is invalid.
     */
    public void signUp(User user) {
        if (user.getEmail() == null || !user.getEmail().matches("^(.+)@(\\S+)$")) {
            throw new ValidationException("Email has invalid format");
        }
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new ValidationException("Password length should be at least 6 characters");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ValidationException("User already signed up");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    /**
     * Authenticates a user with the provided email and password, and returns a JWT token.
     *
     * <p>If authentication is successful, a JWT token is generated and returned in a {@link TokenDto}.
     * Throws an exception if authentication fails or if no user is found with the provided email.</p>
     *
     * @param email    The user's email.
     * @param password The user's password.
     * @return A {@link TokenDto} containing the generated JWT token.
     * @throws org.springframework.security.core.AuthenticationException if authentication fails.
     * @throws NotFoundException                                         if no user with the given email is found.
     */
    public TokenDto logIn(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User", "User not found"));

        return new TokenDto(jwtService.generateToken(user));
    }
}
