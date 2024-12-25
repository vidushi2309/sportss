package com.auth.Authentication.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.Authentication.Repository.NotificationRepository;
import com.auth.Authentication.Repository.UserRepository;
import com.auth.Authentication.dto.NotificationDTO;
import com.auth.Authentication.entity.Notification;
import com.auth.Authentication.entity.User;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    public void createNotification(Long userId, String message, String status) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Notification notification = new Notification();
            notification.setUser(user.get());
            notification.setMessage(message);
            notification.setStatus(status);
            notification.setCreatedAt(LocalDateTime.now());

            notificationRepository.save(notification);
        }
    }

    public List<NotificationDTO> getUserNotifications(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            List<Notification> notifications = notificationRepository.findByUser(user.get());
            return notifications.stream().map(notification -> {
                NotificationDTO dto = new NotificationDTO();
                dto.setNotificationId(notification.getNotificationId());
                dto.setUserId(notification.getUser().getId());
                dto.setMessage(notification.getMessage());
                dto.setStatus(notification.getStatus());
                return dto;
            }).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
