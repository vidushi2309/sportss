package com.auth.Authentication.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registrations")  // Specify the table name
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_id")  // Specify the column name for the primary key in the table
    private Long registrationId;

    // Specify the foreign key column name to refer to 'user_id' in the 'users' table
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")  // "user_id" in the 'registrations' table points to "id" in the 'users' table
    private User user;

    // Specify the foreign key column name to refer to 'event_id' in the 'events' table
    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id")  // "event_id" in the 'registrations' table points to "id" in the 'events' table
    private Event event;

    @Column(name = "status")  // Specify the column name for the status
    private String status;  // Pending, Approved, Rejected

    @Column(name = "registered_at")  // Specify the column name for the registration time
    private LocalDateTime registeredAt;

    // Getters and Setters

    public Long getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Long registrationId) {
        this.registrationId = registrationId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }
}
