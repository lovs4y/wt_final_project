package com.prazhmovska.vladyslava.wt_final_project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for providing JWT token on login.
 *
 * <p>This class encapsulates the user's authentication JWT token.</p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
    private String token;
}
