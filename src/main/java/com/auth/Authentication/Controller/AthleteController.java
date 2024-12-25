package com.auth.Authentication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.Authentication.Services.AthleteService;
import com.auth.Authentication.dto.AthleteRequest;
import com.auth.Authentication.entity.Athlete;

@RestController
@RequestMapping("/api/athlete")
@CrossOrigin(origins = "http://127.0.0.1:5500") // Adjust to match your frontend URL
public class AthleteController {

    @Autowired
    private AthleteService athleteService;

    // Endpoint to save athlete details
    @PostMapping("/{userId}/save")
    public ResponseEntity<?> saveAthleteDetails(@PathVariable Long userId, @RequestBody AthleteRequest request) {
        Athlete athlete = athleteService.saveAthleteDetails(userId, request);
        return ResponseEntity.ok(athlete);
    }

    // Endpoint to fetch athlete details
    @GetMapping("/{userId}/details")
    public ResponseEntity<?> getAthleteDetails(@PathVariable Long userId) {
        Athlete athlete = athleteService.getAthleteDetails(userId);
        if (athlete == null) {
            return ResponseEntity.status(404).body("Athlete details not found for this user.");
        }
        return ResponseEntity.ok(athlete);
    }
}

