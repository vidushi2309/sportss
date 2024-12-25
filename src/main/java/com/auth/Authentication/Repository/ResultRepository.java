package com.auth.Authentication.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.Authentication.entity.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    Optional<Result> findByEventId(Long eventId);
}