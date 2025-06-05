package com.eventhub.eventhub_backend.model;


import com.eventhub.eventhub_backend.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private UserDto user;
}
