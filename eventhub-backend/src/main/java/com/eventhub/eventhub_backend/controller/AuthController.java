package com.eventhub.eventhub_backend.controller;

import com.eventhub.eventhub_backend.dto.UserDto;
import com.eventhub.eventhub_backend.model.AuthRequest;
import com.eventhub.eventhub_backend.model.AuthResponse;
import com.eventhub.eventhub_backend.model.Role;
import com.eventhub.eventhub_backend.model.User;
import com.eventhub.eventhub_backend.repository.UserRepository;
import com.eventhub.eventhub_backend.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    @PostMapping("/register")
    public AuthResponse register(@RequestBody User user){
        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email déja utilisé");
        }
        user.setPassworld(passwordEncoder.encode(user.getPassworld()));
        user.setRole(Role.USER);
        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return new AuthResponse(token, toDto(user));
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassworld())
        );

        User user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé");

                String token = jwtService.generateToken(user);
                return  new AuthResponse(token, toDto(user));

    }

    private UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(userDto.getEmail());
        userDto.setRole(user.getRole());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        return userDto;
    }
}
