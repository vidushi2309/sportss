package com.auth.Authentication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.Authentication.entity.Registration;
import com.auth.Authentication.entity.User;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByStatus(String status);
    List<Registration> findByUser(User user);
}

