package com.prazhmovska.vladyslava.wt_final_project.model.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for user log-in information.
 *
 * <p>This class encapsulates the user's email and password for use in the log-in process.</p>
 */
@Data
public class LogInDto {
    private String email;
    private String password;
}
