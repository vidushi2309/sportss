package com.auth.Authentication.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.Authentication.entity.AssistanceRequest;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssistanceRequestRepository extends JpaRepository<AssistanceRequest, Long> {

    List<AssistanceRequest> findByCoachId(Long coachId);

    Optional<AssistanceRequest> findByRequestId(Long requestId);

    List<AssistanceRequest> findByPlayerId(Long playerId);
}

