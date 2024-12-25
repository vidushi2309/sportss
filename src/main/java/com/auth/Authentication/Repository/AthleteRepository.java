package com.auth.Authentication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.Authentication.entity.Athlete;
public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    Athlete findByUserId(Long userId);
}
