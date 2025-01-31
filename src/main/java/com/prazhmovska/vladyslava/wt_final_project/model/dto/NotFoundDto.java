package com.prazhmovska.vladyslava.wt_final_project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * Data Transfer Object (DTO) for representing "not found" errors.
 * Contains the name of the missing entity and an error comment.
 */
@Data
@AllArgsConstructor
public class NotFoundDto {
    private String entityName;
    private String comment;

}
