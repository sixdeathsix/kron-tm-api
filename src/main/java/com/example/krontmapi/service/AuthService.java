package com.example.krontmapi.service;

import com.example.krontmapi.config.JwtService;
import com.example.krontmapi.dto.AuthRequest;
import com.example.krontmapi.dto.AuthResponse;
import com.example.krontmapi.dto.RegisterRequest;
import com.example.krontmapi.entity.User;
import com.example.krontmapi.entity.enums.Role;
import com.example.krontmapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse registration(RegisterRequest request) throws Exception {

        if(userRepository.existsUserByUsername(request.getUsername())) {
            throw new Exception("Пользователь уже зарегистрирован");
        }

        var user = User.builder()
                .surname(request.getSurname())
                .name(request.getName())
                .patronymic(request.getPatronymic())
                .email(request.getEmail())
                .phone(request.getPhone())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthResponse
                .builder()
                .username(request.getUsername())
                .role(user.getRole())
                .accessToken(jwtToken)
                .build();
    }

    public AuthResponse authorization(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthResponse
                .builder()
                .username(request.getUsername())
                .role(user.getRole())
                .accessToken(jwtToken)
                .build();
    }
}
