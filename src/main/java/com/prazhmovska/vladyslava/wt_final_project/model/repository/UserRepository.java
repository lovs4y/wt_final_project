package com.prazhmovska.vladyslava.wt_final_project.model.repository;

import com.prazhmovska.vladyslava.wt_final_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
