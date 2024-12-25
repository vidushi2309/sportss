package com.auth.Authentication.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.Authentication.Repository.AssistanceRequestRepository;
import com.auth.Authentication.dto.AssistanceRequestDTO;
import com.auth.Authentication.entity.AssistanceRequest;

import java.util.List;
import java.util.Optional;

@Service
public class AssistanceRequestService {

    @Autowired
    private AssistanceRequestRepository assistanceRequestRepository;

    public AssistanceRequest postRequest(AssistanceRequestDTO assistanceRequestDTO) {
        AssistanceRequest request = new AssistanceRequest();
        request.setPlayerId(assistanceRequestDTO.getPlayerId());
        request.setCoachId(assistanceRequestDTO.getCoachId());
        request.setStatus(assistanceRequestDTO.getStatus());
        request.setDescription(assistanceRequestDTO.getDescription());
        return assistanceRequestRepository.save(request);
    }

    public List<AssistanceRequest> getRequestsByCoachId(Long coachId) {
        return assistanceRequestRepository.findByCoachId(coachId);
    }

    public List<AssistanceRequest> getRequestsByPlayerId(Long playerId) {
        return assistanceRequestRepository.findByPlayerId(playerId);
    }

    public Optional<AssistanceRequest> getRequestById(Long requestId) {
        return assistanceRequestRepository.findByRequestId(requestId);
    }

    public AssistanceRequest updateRequestStatus(Long requestId, String status) {
        Optional<AssistanceRequest> existingRequest = assistanceRequestRepository.findByRequestId(requestId);
        if (existingRequest.isPresent()) {
            AssistanceRequest request = existingRequest.get();
            request.setStatus(status);
            return assistanceRequestRepository.save(request);
        } else {
            return null;
        }
    }
}