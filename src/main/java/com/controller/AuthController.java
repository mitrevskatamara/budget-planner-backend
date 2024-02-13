package com.controller;

import com.payload.request.LoginDto;
import com.payload.request.SignUpDto;
import com.payload.response.ResponseDto;
import com.service.AuthService;
import com.service.KeycloakService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final KeycloakService keycloakService;

    @PostMapping("/loginJwt")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
        ResponseDto responseDto = this.authService.login(loginDto);

        return new ResponseEntity<>(responseDto, HttpStatus.valueOf(responseDto.getStatusCode()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        ResponseDto responseDto = this.authService.register(signUpDto);

        return new ResponseEntity<>(responseDto, HttpStatus.valueOf(responseDto.getStatusCode()));
    }

    @PostMapping("/logout")
    public ResponseDto logout(HttpServletRequest request) throws ServletException {
        return this.authService.logout(request);
    }

    @GetMapping("/currentUser")
    public ResponseDto getCurrentUser(Authentication authentication) {
        return this.authService.getCurrentUser(authentication);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@NotNull @RequestBody LoginDto loginDto) {
        ResponseDto responseDto = this.keycloakService.authenticateKeycloakUser(loginDto);
        return new ResponseEntity<>(responseDto, HttpStatus.valueOf(responseDto.getStatusCode()));
    }

}