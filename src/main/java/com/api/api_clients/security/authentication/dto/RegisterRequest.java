package com.api.api_clients.security.authentication.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}
