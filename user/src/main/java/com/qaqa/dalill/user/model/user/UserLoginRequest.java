package com.qaqa.dalill.user.model.user;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginRequest {
    private String email;
    @Size(min = 8, max = 50)
    private String password;
}
