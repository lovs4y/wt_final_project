package com.prazhmovska.vladyslava.wt_final_project.model.dto;

import lombok.Data;

@Data
public class TokenDto {
    private String token;
    private String email;
    private Long id;
    public TokenDto(String email, Long id) {
        this.email = email;
        this.id = id;
    }
}
