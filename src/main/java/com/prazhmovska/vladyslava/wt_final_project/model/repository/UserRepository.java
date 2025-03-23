package com.prazhmovska.vladyslava.wt_final_project.model.repository;

import com.prazhmovska.vladyslava.wt_final_project.model.User;
import com.prazhmovska.vladyslava.wt_final_project.model.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for accessing {@link User} entities.
 * Provides methods to perform CRUD operations and custom queries on the "users" table.
 *
 * <p>This interface extends {@link JpaRepository}.</p>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Finds a user by their email address.
     *
     * @param email the email address of the user to search for
     * @return the {@link User} with the specified email, or {@code null} if not found
     */
    Optional<User> findByEmail(String email);

    /**
     * Finds user details by user's email.
     *
     * @param email is a user email.
     * @return user details {@link UserDto}
     */
    @Query("SELECT new com.prazhmovska.vladyslava.wt_final_project.model.dto.UserDto(u.id, u.name, u.email, u.password) " +
            "FROM User u WHERE u.email = :email")
    Optional<UserDto> findUserDetailsByEmail(@Param("email") String email);

    /**
     * Check user already exists in DB.
     *
     * @param email user email.
     * @return boolean true if user exists otherwise false.
     */
    boolean existsByEmail(String email);
}
