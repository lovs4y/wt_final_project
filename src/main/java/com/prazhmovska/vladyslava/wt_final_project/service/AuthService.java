package com.prazhmovska.vladyslava.wt_final_project.service;

import com.prazhmovska.vladyslava.wt_final_project.core.exceptions.ValidationException;
import com.prazhmovska.vladyslava.wt_final_project.model.User;
import com.prazhmovska.vladyslava.wt_final_project.model.dto.TokenDto;
import com.prazhmovska.vladyslava.wt_final_project.model.repository.UserRepository;
import org.springframework.stereotype.Service;
/**
* Authentification class that signs up/logs in a user and checks
 * if the password and email formats are correct.
 */
@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    /**
     *
     * Method responsible for checking validity of format
     * of the password and email while signing up or logging in.
     */
    public void signUp(User user) {
        if (user.getEmail() == null || !user.getEmail().matches("^(.+)@(\\S+)$")) {
            throw new ValidationException("Email has invalid format");
        }
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new ValidationException("Password length should be at least 6 characters");
        }
        userRepository.save(user);
    }

    public TokenDto logIn(String email, String password) {

    }
}
