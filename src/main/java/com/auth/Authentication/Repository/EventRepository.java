package com.auth.Authentication.Repository;


import com.auth.Authentication.entity.Event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByStatus(String status);
}
