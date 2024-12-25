package com.auth.Authentication.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.Authentication.Repository.EventRepository;
import com.auth.Authentication.dto.EventDTO;
import com.auth.Authentication.entity.Event;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setTitle(eventDTO.getTitle());
        event.setDate(eventDTO.getDate());
        event.setMeet(eventDTO.getMeet());
        event.setVenue(eventDTO.getVenue());
        event.setCategory(eventDTO.getCategory());
        event.setDescription(eventDTO.getDescription());
        event.setStatus(eventDTO.getStatus());
        return eventRepository.save(event);
    }

    public List<Event> getEventsByStatus(String status) {
        return eventRepository.findByStatus(status);
    }
}
