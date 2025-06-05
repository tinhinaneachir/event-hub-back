package com.eventhub.eventhub_backend.model;


import com.eventhub.eventhub_backend.dto.UserDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor

public class AuthResponse {
    private String token;
    private UserDto user;
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
