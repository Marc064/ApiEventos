package com.edu.uptc.apieventos.services;

import com.edu.uptc.apieventos.entities.Event;
import com.edu.uptc.apieventos.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public List<Event> findAll(){
        return eventRepository.findAll();
    }

    public Event findById(Integer id){
        Optional<Event> optional = eventRepository.findById(id);

        return optional.isPresent() ? optional.get():null;
    }

    public Event save (Event event){
        return eventRepository.save(event);
    }
}
