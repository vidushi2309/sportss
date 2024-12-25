package com.auth.Authentication.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.Authentication.Repository.RegistrationRepository;
import com.auth.Authentication.dto.RegistrationDTO;
import com.auth.Authentication.entity.Registration;

@Service
public class AdminService {
    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private NotificationService notificationService;

    public String updateRegistrationStatus(Long registrationId, String status) {
        Optional<Registration> registration = registrationRepository.findById(registrationId);
        if (registration.isPresent()) {
            Registration reg = registration.get();
            reg.setStatus(status);
            registrationRepository.save(reg);
    
            // Notify the user using the userId and eventId from Registration
            String message = "Your registration for event ID " + reg.getEvent().getId() + " has been " + status;
            notificationService.createNotification(reg.getUser().getId(), message, status);
    
            return "Registration status updated to " + status;
        }
        return "Registration not found";
    }
    
    

    public List<RegistrationDTO> getPendingRequests() {
        List<Registration> registrations = registrationRepository.findByStatus("Pending");
        return registrations.stream().map(reg -> {
            RegistrationDTO dto = new RegistrationDTO();
            dto.setRegistrationId(reg.getRegistrationId());
    
            // Map userId and eventId from the Registration entity
            dto.setUserId(reg.getUser().getId());
            dto.setEventId(reg.getEvent().getId());
            
            dto.setStatus(reg.getStatus());
            dto.setRegisteredAt(reg.getRegisteredAt());
            return dto;
        }).collect(Collectors.toList());
    }
    
}

