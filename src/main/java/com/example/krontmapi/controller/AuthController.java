package com.example.krontmapi.controller;

import com.example.krontmapi.dto.AuthRequest;
import com.example.krontmapi.dto.AuthResponse;
import com.example.krontmapi.dto.RegisterRequest;
import com.example.krontmapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity registration(
            @RequestBody RegisterRequest request
    ) {
        try {
            return ResponseEntity.ok(authService.registration(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/autharization")
    public ResponseEntity authorization(
            @RequestBody AuthRequest request
    ) {
        try {
            return ResponseEntity.ok(authService.authorization(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
