package com.prazhmovska.vladyslava.wt_final_project;

import com.prazhmovska.vladyslava.wt_final_project.model.Note;
import com.prazhmovska.vladyslava.wt_final_project.model.Notebook;
import com.prazhmovska.vladyslava.wt_final_project.model.User;
import com.prazhmovska.vladyslava.wt_final_project.model.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

/**
 * There everything begins ;)
 */
@SpringBootApplication
public class WtFinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WtFinalProjectApplication.class, args);
    }

    /**
     * Creates initial data for application testing.
     *
     * @param userRepository used to save mocked users, notebooks and notes will be saved
     *                       because relations have {@link jakarta.persistence.CascadeType#ALL}.
     */
    // actual password is: 123456789
    // password: $2a$10$jcgLMQR3WHVGcKKztXxEFe1SicPduR0uJ8PUi135d/finVk04.V/y
    @Bean
    ApplicationRunner initialData(UserRepository userRepository) {
        return args -> {
            Note firstNote = new Note(
                    null,
                    "First Note",
                    LocalDateTime.now(),
                    null,
                    "Mocked content"
            );
            Note secondNote = new Note(
                    null,
                    "Second Note",
                    LocalDateTime.now(),
                    null,
                    "Mocked content"
            );
            Note thirdNote = new Note(
                    null,
                    "Third Note",
                    LocalDateTime.now(),
                    null,
                    "Mocked content"
            );

            Notebook notebook = new Notebook(
                    null,
                    "First notebook",
                    LocalDateTime.now(),
                    List.of(firstNote, secondNote, thirdNote)
            );

            userRepository.save(new User(
                    null,
                    "Initial User 1",
                    "useremail@email.com",
                    "$2a$10$jcgLMQR3WHVGcKKztXxEFe1SicPduR0uJ8PUi135d/finVk04.V/y",
                    List.of(notebook))
            );
        };
    }
}
