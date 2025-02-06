package com.prazhmovska.vladyslava.wt_final_project.service;

import com.prazhmovska.vladyslava.wt_final_project.core.exceptions.NotFoundException;
import com.prazhmovska.vladyslava.wt_final_project.core.exceptions.ValidationException;
import com.prazhmovska.vladyslava.wt_final_project.model.User;
import com.prazhmovska.vladyslava.wt_final_project.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing users.
 * Provides methods for CRUD operations on {@link User} entities.
 * Interacts with {@link UserRepository} for data access and validation.
 */
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public List<User> list() {
        return userRepository.findAll();
    }

    /**
     * Creates a new user.
     *
     * <p>Validates that the user has an email before saving. If the email is missing, a {@link ValidationException} is thrown.</p>
     * <p>
     * {@link User} entity characteristics to create
     *
     * @return the saved {@link User} entity bla bla bla
     * @throws ValidationException if no email is provided
     */
    public User create(User user) {
        if (user.getEmail() == null) {
            throw new ValidationException("No email provided");
        }
        return userRepository.save(user);
    }

    /**
     * Retrieves a user by their ID.
     *
     * <p>If no user with the specified ID is found, throws a {@link NotFoundException}.</p>
     *
     * @param id the ID of the user to retrieve
     * @return the {@link User} entity with the specified ID
     * @throws NotFoundException if no user is found with the given ID
     */
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User", "user not found"));
    }

    /**
     * Gets a user by their email.
     *
     * <p>Fetches a user by their email address.</p>
     *
     * @param email the email of the user to retrieve
     * @return the {@link User} entity with the specified email
     */
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User", "User not found"));
    }

    /**
     * Updates an existing user.
     *
     * <p>Sets the user’s ID before saving, existing user is updated, not a new one.</p>
     *
     * @param user the updated {@link User} entity
     * @param id   the ID of the user to update
     * @return the updated {@link User} entity
     */
    public User update(User user, Long id) {
        user.setId(id);

        userRepository.findById(id).ifPresentOrElse(
                existing -> user.setPassword(existing.getPassword()),
                () -> {
                    throw new NotFoundException("User", "user not found");
                }
        );

        return userRepository.save(user);
    }

}
