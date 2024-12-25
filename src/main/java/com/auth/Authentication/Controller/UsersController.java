package com.auth.Authentication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.Authentication.Services.RegistrationService;
import com.auth.Authentication.dto.RegistrationDTO;

@RestController
@RequestMapping("/api/user/registrations")
@CrossOrigin(origins = "http://127.0.0.1:5500") // Adjust to match your frontend URL

public class UsersController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<RegistrationDTO>> getUserRegistrations(@PathVariable Long userId) {
        List<RegistrationDTO> registrations = registrationService.getUserRegistrations(userId);
        return ResponseEntity.ok(registrations);
    }
}

