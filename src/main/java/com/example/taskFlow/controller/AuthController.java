package com.example.taskFlow.controller;

import com.example.taskFlow.dto.auth.AuthRequest;
import com.example.taskFlow.dto.auth.AuthResponse;
import com.example.taskFlow.dto.auth.RegisterRequest;
import com.example.taskFlow.entity.Role;
import com.example.taskFlow.entity.User;
import com.example.taskFlow.service.JwtService;
import com.example.taskFlow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskFlow.dto.AuthDTO;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

        private final UserService userService;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;
        private final PasswordEncoder passwordEncoder;

        @PostMapping("/register")
        public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {

                if (userService.existsByEmail(request.getEmail())) {
                        return ResponseEntity.badRequest().body(
                                        AuthResponse.builder()
                                                        .message("Usuario ya registrado con ese email.")
                                                        .build());
                }

                var user = User.builder()
                                .firstName(request.getFirstName())
                                .lastName(request.getLastName())
                                .email(request.getEmail())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .role(Role.USER)
                                .build();

                userService.save(user);

                var jwtToken = jwtService.generateToken(user);
                return ResponseEntity.ok(AuthResponse.builder()
                                .token(jwtToken)
                                .build());
        }

        @PostMapping("/login")
        public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
                try {
                        authenticationManager.authenticate(
                                        new UsernamePasswordAuthenticationToken(
                                                        request.getEmail(),
                                                        request.getPassword()));
                        var user = userService.loadUserByUsername(request.getEmail());
                        var jwtToken = jwtService.generateToken(user);
                        return ResponseEntity.ok(AuthResponse.builder()
                                        .token(jwtToken)
                                        .build());
                } catch (org.springframework.security.authentication.BadCredentialsException ex) {
                        return ResponseEntity.status(401).body(
                                        AuthResponse.builder()
                                                        .message("Credenciales incorrectas.")
                                                        .build());
                }
        }

        @GetMapping("/me")
        public ResponseEntity<AuthDTO> getCurrentUser() {
                User user = userService.getCurrentUser();
                AuthDTO authDTO = new AuthDTO();
                authDTO.setFirstName(user.getFirstName());
                authDTO.setLastName(user.getLastName());
                authDTO.setEmail(user.getEmail());
                return ResponseEntity.ok(authDTO);
        }
}