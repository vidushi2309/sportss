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

@Entity
@Table(name = "notifications")  // Specify the table name
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")  // Specify the column name for the primary key in the table
    private Long notificationId;

    // Specify the foreign key column name to refer to 'user_id' in the 'users' table
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")  // "user_id" in the 'notifications' table points to "id" in the 'users' table
    private User user;

    @Column(name = "message")  // Specify the column name for the message
    private String message;

    @Column(name = "status")  // Specify the column name for the status
    private String status;

    @Column(name = "created_at")  // Specify the column name for the creation timestamp
    private LocalDateTime createdAt;

    // Getters and Setters

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

