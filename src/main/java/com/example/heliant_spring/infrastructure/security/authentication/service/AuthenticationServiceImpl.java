package com.example.heliant_spring.infrastructure.security.authentication.service;


import com.example.heliant_spring.infrastructure.security.authentication.dto.SignInRequestDTO;
import com.example.heliant_spring.infrastructure.security.authentication.dto.SignInResponseDTO;
import com.example.heliant_spring.infrastructure.security.service.JwtTokenService;
import com.example.heliant_spring.infrastructure.security.user_details.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    @Override
    public SignInResponseDTO signIn(SignInRequestDTO request) {
        String username = request.username();
        String password = request.password();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String accessToken = jwtTokenService.createToken(userDetails);

            return SignInResponseDTO.builder()
                    .accessToken(accessToken)
                    .build();
        }catch (AuthenticationException e){
            throw new BadCredentialsException("Your username or password is incorrect");
        }
    }
}
