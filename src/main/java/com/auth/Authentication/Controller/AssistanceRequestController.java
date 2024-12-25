package com.auth.Authentication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.auth.Authentication.Services.AssistanceRequestService;
import com.auth.Authentication.dto.AssistanceRequestDTO;
import com.auth.Authentication.entity.AssistanceRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/assistance-requests")
public class AssistanceRequestController {

    @Autowired
    private AssistanceRequestService assistanceRequestService;

    // POST: Player submits an assistance request to a specific coach
    @PostMapping("/post")
    public ResponseEntity<AssistanceRequest> postAssistanceRequest(@RequestBody AssistanceRequestDTO assistanceRequestDTO) {
        AssistanceRequest request = assistanceRequestService.postRequest(assistanceRequestDTO);
        return ResponseEntity.ok(request);
    }

    // GET: Coach sees all assistance requests sent to them
    @GetMapping("/coach/{coachId}")
    public ResponseEntity<List<AssistanceRequest>> getRequestsByCoachId(@PathVariable Long coachId) {
        List<AssistanceRequest> requests = assistanceRequestService.getRequestsByCoachId(coachId);
        return ResponseEntity.ok(requests);
    }

    // PUT: Coach approves or rejects the assistance request
    @PutMapping("/update/{requestId}")
    public ResponseEntity<AssistanceRequest> updateRequestStatus(@PathVariable Long requestId, @RequestParam String status) {
        AssistanceRequest updatedRequest = assistanceRequestService.updateRequestStatus(requestId, status);
        return updatedRequest != null ? ResponseEntity.ok(updatedRequest) : ResponseEntity.notFound().build();
    }

    // GET: Player sees their updated assistance request status
    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<AssistanceRequest>> getRequestsByPlayerId(@PathVariable Long playerId) {
        List<AssistanceRequest> requests = assistanceRequestService.getRequestsByPlayerId(playerId);
        return ResponseEntity.ok(requests);
    }

    // GET: Player can see specific assistance request
    @GetMapping("/request/{requestId}")
    public ResponseEntity<AssistanceRequest> getRequestById(@PathVariable Long requestId) {
        Optional<AssistanceRequest> request = assistanceRequestService.getRequestById(requestId);
        return request.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
