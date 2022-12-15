package com.example.BACKEND.controller;

import com.example.BACKEND.ObjectWrapper;
import com.example.BACKEND.dto.insert.EventInsertDto;
import com.example.BACKEND.dto.response.EventResponseDto;
import com.example.BACKEND.dto.update.EventUpdateDto;
import com.example.BACKEND.service.interfaces.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("event")
@RestController
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ObjectWrapper<EventResponseDto> addEvent(@RequestBody EventInsertDto eventInsertDto){
        return eventService.addEvent(eventInsertDto);
    }
    @GetMapping(path="{id}")
    public ObjectWrapper<EventResponseDto> getEventById(@PathVariable Long id){
        return eventService.getEventById(id);
    }

    @PutMapping
    public ObjectWrapper<EventResponseDto> updateEvent(@RequestBody EventUpdateDto eventUpdateDto){
        return eventService.updateEvent(eventUpdateDto);
    }

    @DeleteMapping(path="{id}")
    public ObjectWrapper<EventResponseDto> deleteEventById(@PathVariable Long id){
        return eventService.deleteEventById(id);
    }
}
