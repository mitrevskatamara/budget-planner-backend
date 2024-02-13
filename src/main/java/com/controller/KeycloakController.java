package com.controller;

import com.payload.response.ResponseDto;
import com.security.jwt.JwtUtils;
import com.service.KeycloakService;
import lombok.AllArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/keycloak")
@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
public class KeycloakController {

    private final KeycloakService keycloakService;

    private final JwtUtils jwtUtils;

    @GetMapping("/findByUsername")
    public UserRepresentation getKeycloakUserByUsername(@RequestParam String username) {
        return this.keycloakService.findByEmail(username);
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<ResponseDto> sendEmail(@RequestParam String email) {
        ResponseDto r = this.keycloakService.sendMailForUpdatingPassword(email);
        return new ResponseEntity<>(r, HttpStatus.valueOf(r.getStatusCode()));
    }

    @PostMapping("/updatePassword")
    public void sendEmail(@RequestParam String token, @RequestParam String username) {
        this.keycloakService.updatePassword(token, username);
    }

    @PostMapping("/validateToken")
    public boolean validateToken(@RequestParam String token) {
        return this.jwtUtils.validateJwtToken(token);
    }

    @PostMapping("/sendVerifyEmail")
    public void sendVerifyEmail(@RequestParam String email) {
        this.keycloakService.sendVerifyEmail(email);
    }

    @PostMapping("/sendVerifyEmailAgain")
    public void sendVerifyEmailAgain(@RequestParam String token) {
        this.keycloakService.sendVerifyEmailAgain(token);
    }

    @PostMapping("/setEmailVerified")
    public void setEmailVerified(@RequestParam String token) {
        this.keycloakService.setEmailVerified(token);
    }

}
