package com.auth.Authentication.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.Authentication.Repository.EventRepository;
import com.auth.Authentication.Repository.RegistrationRepository;
import com.auth.Authentication.Repository.UserRepository;
import com.auth.Authentication.dto.RegistrationDTO;
import com.auth.Authentication.entity.Event;
import com.auth.Authentication.entity.Registration;
import com.auth.Authentication.entity.User;

@Service
public class RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    public RegistrationDTO registerUser(RegistrationDTO registrationDTO) {
        Optional<User> user = userRepository.findById(registrationDTO.getUserId());
        Optional<Event> event = eventRepository.findById(registrationDTO.getEventId());

        if (user.isPresent() && event.isPresent()) {
            Registration registration = new Registration();
            registration.setUser(user.get());
            registration.setEvent(event.get());
            registration.setStatus("Pending");
            registration.setRegisteredAt(LocalDateTime.now());

            registrationRepository.save(registration);

            registrationDTO.setRegistrationId(registration.getRegistrationId());
            registrationDTO.setStatus(registration.getStatus());
            return registrationDTO;
        }
        return null; // Handle error appropriately
    }

    public List<RegistrationDTO> getUserRegistrations(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            List<Registration> registrations = registrationRepository.findByUser(user.get());
            return registrations.stream().map(reg -> {
                RegistrationDTO dto = new RegistrationDTO();
                dto.setRegistrationId(reg.getRegistrationId());
                dto.setUserId(reg.getUser().getId());
                dto.setEventId(reg.getEvent().getId());
                dto.setStatus(reg.getStatus());
                return dto;
            }).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
