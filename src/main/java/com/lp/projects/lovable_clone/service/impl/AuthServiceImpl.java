package com.lp.projects.lovable_clone.service.impl;

import com.lp.projects.lovable_clone.dto.auth.AuthResponse;
import com.lp.projects.lovable_clone.dto.auth.LoginRequest;
import com.lp.projects.lovable_clone.dto.auth.SignupRequest;
import com.lp.projects.lovable_clone.dto.auth.UserProfleResponse;
import com.lp.projects.lovable_clone.entity.User;
import com.lp.projects.lovable_clone.mapper.UserMapper;
import com.lp.projects.lovable_clone.repository.UserRepository;
import com.lp.projects.lovable_clone.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;

    UserMapper userMapper;

    @Override
    public UserProfleResponse signup(SignupRequest request) {

        User user = User
                .builder()
                .name(request.name())
                .email(request.email())
                .passwordHash(request.password())
                .build();

        user = userRepository.save(user);

        return userMapper.userToUserProfileResponse(user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
    }
}
