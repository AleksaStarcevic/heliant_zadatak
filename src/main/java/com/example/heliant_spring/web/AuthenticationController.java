package com.example.heliant_spring.web;

import com.example.heliant_spring.infrastructure.security.authentication.dto.SignInRequestDTO;
import com.example.heliant_spring.infrastructure.security.authentication.dto.SignInResponseDTO;
import com.example.heliant_spring.infrastructure.security.authentication.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "User sign in with username and password", description = "After successful authentication user " +
            "gets jwt token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful sign in",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SignInResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Input validation failed",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Bad Credentials")})
    @PostMapping("/signIn")
    public ResponseEntity<SignInResponseDTO> signIn(@RequestBody @Valid SignInRequestDTO request) {
        return ResponseEntity.ok(authenticationService.signIn(request));
    }
}
