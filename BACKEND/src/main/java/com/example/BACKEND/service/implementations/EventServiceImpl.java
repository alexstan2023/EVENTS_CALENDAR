package com.example.BACKEND.service.implementations;

import com.example.BACKEND.ObjectWrapper;
import com.example.BACKEND.dto.insert.EventInsertDto;
import com.example.BACKEND.dto.response.EventResponseDto;
import com.example.BACKEND.dto.update.EventUpdateDto;
import com.example.BACKEND.model.Event;
import com.example.BACKEND.repository.EventRepository;
import com.example.BACKEND.service.interfaces.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public ObjectWrapper<EventResponseDto> addEvent(EventInsertDto eventInsertDto) {
        if(eventInsertDto.isValid()) {
            Event eventToSafe = new Event(eventInsertDto.getName(),eventInsertDto.getStartDate(),eventInsertDto.getEndDate());
            eventRepository.save(eventToSafe);
            return new ObjectWrapper<>(200,"Event inserted succesfully!",
                    new EventResponseDto(eventToSafe.getId(),eventToSafe.getName(),
                            eventToSafe.getStartDate(),eventToSafe.getEndDate()));
        }
        else{
            return new ObjectWrapper<>(400,"Invalid dates!",null);
        }
    }

    @Override
    public ObjectWrapper<EventResponseDto> getEventById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if(event.isPresent()){
            return new ObjectWrapper<>(200,"Event found!",new EventResponseDto(event.get().getId(),
                    event.get().getName(),event.get().getStartDate(), event.get().getEndDate()));
        }
        else{
            return new ObjectWrapper<>(404,"Event not found!",null);
        }
    }

    @Override
    public ObjectWrapper<EventResponseDto> updateEvent(EventUpdateDto eventUpdateDto) {
        Optional<Event> event = eventRepository.findById(eventUpdateDto.getId());
        if(event.isPresent()){
            if(eventUpdateDto.isValid()){
                event.get().setName(eventUpdateDto.getName());
                event.get().setStartDate(eventUpdateDto.getEndDate());
                event.get().setEndDate(eventUpdateDto.getEndDate());
                eventRepository.save(event.get());
                return new ObjectWrapper<>(200,"Event updated succesfully!",
                        new EventResponseDto(event.get().getId(), event.get().getName(),
                                event.get().getStartDate(), event.get().getEndDate()));
            }
            else{
                return new ObjectWrapper<>(400,"Invalid dates!",null);
            }
        }
        else{
            return new ObjectWrapper<>(404,"Event not found!",null);
        }
    }

    @Override
    public ObjectWrapper<EventResponseDto> deleteEventById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if(event.isPresent()){
            eventRepository.deleteById(id);
            return new ObjectWrapper<>(200,"Event deleted succesfully!",
                    new EventResponseDto(event.get().getId(),event.get().getName(),
                            event.get().getStartDate(),event.get().getEndDate()));
        }
        else{
            return new ObjectWrapper<>(404,"Event not found",null);
        }
    }
}
