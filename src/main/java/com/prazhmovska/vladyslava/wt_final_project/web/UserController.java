package com.prazhmovska.vladyslava.wt_final_project.web;

import com.prazhmovska.vladyslava.wt_final_project.model.User;
import com.prazhmovska.vladyslava.wt_final_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * REST controller for managing users.
 * Provides endpoints for user-related operations such as retrieval, creation, and updates.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    /**
     * Retrieves a list of all users.
     *
     * @return a {@link List} of {@link User} entities
     */
    @GetMapping
    public List<User> list() {
        return userService.list();
    }
    /**
     * Retrieves a user by their ID.
     *
     * @param id of the user to retrieve
     * @return the {@link User} entity with the specified ID
     */
    @GetMapping("/{id}")
    public User getById(@PathVariable long id) {
        return userService.getById(id);
    }
    //todo: to be removed after implementation of authentication
    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }
    /**
     * Updates an existing user.
     *
     * @param user the updated {@link User} entity
     * @param id of the user to update
     * @return the updated {@link User} entity
     */
    @PutMapping("/{id}")
    public User update(@RequestBody User user, @PathVariable long id) {
        return userService.update(user, id);
    }

}