package com.example.heliant_spring.infrastructure.security.authentication.service;


import com.example.heliant_spring.infrastructure.security.authentication.dto.SignInRequestDTO;
import com.example.heliant_spring.infrastructure.security.authentication.dto.SignInResponseDTO;

public interface AuthenticationService {

    SignInResponseDTO signIn(SignInRequestDTO request);

}