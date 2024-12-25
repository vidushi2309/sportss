package com.auth.Authentication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.Authentication.Services.RegistrationService;
import com.auth.Authentication.dto.RegistrationDTO;

@RestController
@RequestMapping("/api/register")
@CrossOrigin(origins = "http://127.0.0.1:5500") // Adjust to match your frontend URL

public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<RegistrationDTO> registerUser(@RequestBody RegistrationDTO registrationDTO) {
        RegistrationDTO result = registrationService.registerUser(registrationDTO);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
