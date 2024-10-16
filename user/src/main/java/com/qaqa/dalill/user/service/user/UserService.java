package com.qaqa.dalill.user.service.user;

import com.qaqa.dalill.user.model.token.TokenResponse;
import com.qaqa.dalill.user.model.user.UserLoginRequest;

public interface UserService {
    void signup(UserLoginRequest request);
    TokenResponse login(UserLoginRequest request);
    boolean verify(UserLoginRequest request);
}
