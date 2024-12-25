package com.auth.Authentication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.Authentication.Services.AdminService;
import com.auth.Authentication.dto.RegistrationDTO;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://127.0.0.1:5500") // Adjust to match your frontend URL

public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/requests")
    public ResponseEntity<List<RegistrationDTO>> getPendingRequests() {
        List<RegistrationDTO> requests = adminService.getPendingRequests();
        return ResponseEntity.ok(requests);
    }

    @PutMapping("/request/update")
    public ResponseEntity<String> updateRegistrationStatus(@RequestBody RegistrationDTO registrationDTO) {
        String result = adminService.updateRegistrationStatus(registrationDTO.getRegistrationId(), registrationDTO.getStatus());
        return ResponseEntity.ok(result);
    }
}
