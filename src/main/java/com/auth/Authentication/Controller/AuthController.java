package com.auth.Authentication.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.Authentication.Services.UserService;
import com.auth.Authentication.dto.LoginRequest;
import com.auth.Authentication.dto.RegisterRequest;
import com.auth.Authentication.entity.User;
import com.auth.Authentication.exception.ApiException;
import com.auth.Authentication.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://127.0.0.1:5500") // Ensure this matches your frontend URL
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // Signup endpoint
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            // Register the user
            User user = userService.registerUser(request);
            return ResponseEntity.ok().body("Signup successful");
        } catch (ApiException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error during signup: " + e.getMessage());
        }
    }

    // Login endpoint
    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    try {
        // Authenticate user
        User user = userService.authenticateUser(request);

        // Generate JWT token
        String token = jwtTokenProvider.generateToken(user.getUsername());

        // Extract role names from the user object
        String role = user.getRoles().stream()
                .map(r -> r.getName()) // Assuming Role has a `getName()` method
                .collect(Collectors.joining(", ")); // Handle multiple roles if needed

        // Extract userId from the authenticated user
        Long userId = user.getId(); // Assuming `User` has a `getId()` method

        // Custom response with success message, token, role, and userId
        Map<String, Object> response = new HashMap<>();
        response.put("message", "You are logged in successfully");
        response.put("token", token);
        response.put("role", role);  // Add role name to the response
        response.put("userId", userId); // Add userId to the response

        return ResponseEntity.ok(response);
    } catch (ApiException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred during login: " + e.getMessage());
    }
}

}
