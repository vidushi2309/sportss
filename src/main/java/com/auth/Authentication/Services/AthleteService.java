package com.auth.Authentication.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.Authentication.Repository.AthleteRepository;
import com.auth.Authentication.Repository.UserRepository;
import com.auth.Authentication.dto.AthleteRequest;
import com.auth.Authentication.entity.Athlete;
import com.auth.Authentication.entity.User;

@Service
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private UserRepository userRepository;

    // Save athlete details
    public Athlete saveAthleteDetails(Long userId, AthleteRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Athlete athlete = athleteRepository.findByUserId(userId);
        if (athlete == null) {
            athlete = new Athlete();
            athlete.setUser(user);
        }

        athlete.setName(request.getName());
        athlete.setDob(request.getDob());
        athlete.setGender(request.getGender());
        athlete.setHeight(request.getHeight());
        athlete.setWeight(request.getWeight());
        athlete.setCategory(request.getCategory());
        athlete.setCoach(request.getCoach());

        return athleteRepository.save(athlete);
    }

    // Fetch athlete details by user ID
    public Athlete getAthleteDetails(Long userId) {
        return athleteRepository.findByUserId(userId);
    }
    
}

