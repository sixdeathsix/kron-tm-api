package com.example.krontmapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {
    private String surname;
    private String name;
    private String patronymic;
    private String email;
    private String phone;
    private String username;
    private String password;
}
