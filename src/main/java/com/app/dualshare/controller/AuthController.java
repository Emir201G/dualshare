package com.app.dualshare.controller;

import com.app.dualshare.dto.TokenRequestDTO;
import com.app.dualshare.dto.UserResponseDTO;
import com.app.dualshare.service.impl.AuthServiceImpl;
import com.app.dualshare.service.interfaces.IAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final IAuthService authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }
    @PostMapping("/verify")
    public ResponseEntity<UserResponseDTO> verifyToken(@RequestBody TokenRequestDTO tokenRequestDTO){
        UserResponseDTO userResponseDTO = authService.verifyAuthenticate(tokenRequestDTO);
        return ResponseEntity.ok(userResponseDTO);
    }
}
