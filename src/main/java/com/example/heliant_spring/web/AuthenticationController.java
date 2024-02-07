package com.example.heliant_spring.web;

import com.example.heliant_spring.infrastructure.security.authentication.dto.SignInRequestDTO;
import com.example.heliant_spring.infrastructure.security.authentication.dto.SignInResponseDTO;
import com.example.heliant_spring.infrastructure.security.authentication.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signIn")
    public ResponseEntity<SignInResponseDTO> signIn(@RequestBody @Valid SignInRequestDTO request) {
        return ResponseEntity.ok(authenticationService.signIn(request));
    }
}
