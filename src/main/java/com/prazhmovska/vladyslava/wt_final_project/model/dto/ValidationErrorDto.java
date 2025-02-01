package com.prazhmovska.vladyslava.wt_final_project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for validation error details.
 * Contains a comment describing the validation issue.
 */
@Data
@AllArgsConstructor
public class ValidationErrorDto {
    private String comment;
}
