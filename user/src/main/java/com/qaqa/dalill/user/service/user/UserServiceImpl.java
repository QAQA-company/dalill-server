package com.qaqa.dalill.user.service.user;

import com.qaqa.dalill.user.model.token.TokenResponse;
import com.qaqa.dalill.user.model.user.User;
import com.qaqa.dalill.user.model.user.UserLoginRequest;
import com.qaqa.dalill.user.repository.UserRepository;
import com.qaqa.dalill.user.security.util.PasswordUtil;
import com.qaqa.dalill.user.service.messaging.JwtTokenListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordUtil passwordUtil;
    private final JwtTokenListener jwtTokenListener;

    @Override
    public void signup(UserLoginRequest request) {
        User build = User.builder()
                .email(request.getEmail())
                .password(passwordUtil.encodePassword(request.getPassword()))
                .build();

        userRepository.save(build);
    }

    @Override
    public TokenResponse login(UserLoginRequest request) {
        String token = jwtTokenListener.getToken(request.getEmail());
        return new TokenResponse(token);
    }

    @Override
    public boolean verify(UserLoginRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return passwordUtil.matches(
                    request.getPassword(),
                    userRepository.findByEmail(request.getEmail()).get().getPassword()
            );
        }
        return false;
    }
}
