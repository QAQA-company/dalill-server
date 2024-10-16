package com.qaqa.dalill.user.model.token;

import lombok.Data;

@Data
public class TokenResponse {
    private String token;

    public TokenResponse(String token) {
        this.token = token;
    }
}
