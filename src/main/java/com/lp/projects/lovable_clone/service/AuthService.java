package com.lp.projects.lovable_clone.service;

import com.lp.projects.lovable_clone.dto.auth.AuthResponse;
import com.lp.projects.lovable_clone.dto.auth.LoginRequest;
import com.lp.projects.lovable_clone.dto.auth.SignupRequest;

public interface AuthService {

    AuthResponse signup(SignupRequest request);

    AuthResponse login(LoginRequest request);
}
