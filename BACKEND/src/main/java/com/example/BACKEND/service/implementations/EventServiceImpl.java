package com.example.BACKEND.service.implementations;

import com.example.BACKEND.ObjectWrapper;
import com.example.BACKEND.dto.insert.EventInsertDto;
import com.example.BACKEND.dto.response.EventResponseDto;
import com.example.BACKEND.dto.update.EventUpdateDto;
import com.example.BACKEND.mapper.EventMappers;
import com.example.BACKEND.model.Event;
import com.example.BACKEND.repository.EventRepository;
import com.example.BACKEND.service.interfaces.EventService;
import com.example.BACKEND.utils.TimeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final TimeInfo timeInfo;
    private final EventMappers eventMappers;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, TimeInfo timeInfo, EventMappers eventMappers) {
        this.eventRepository = eventRepository;
        this.timeInfo = timeInfo;
        this.eventMappers = eventMappers;
    }

    @Override
    public ObjectWrapper<EventResponseDto> addEvent(EventInsertDto eventInsertDto) {
        List<String> existingEventNames = eventRepository.getAllNames();
        Boolean isAnExistingName = existingEventNames.contains(eventInsertDto.getName());
        if(isAnExistingName){
            return new ObjectWrapper<>(400,"Name already exist!",null);
        }
        String cannotBeCreatedMessage = eventInsertDto.canBeCreated(eventRepository.getAll());
        if(!cannotBeCreatedMessage.equals("OK")){
            return new ObjectWrapper<>(400,cannotBeCreatedMessage,null);
        }
        Event event = eventMappers.eventInsertDtoToEventMapper(eventInsertDto);
        eventRepository.save(event);
        EventResponseDto responseEntity = eventMappers.eventToEventResponseDtoMapper(event);
        return new ObjectWrapper<>(200,"Event inserted successfully!",responseEntity);
    }

    @Override
    public ObjectWrapper<List<EventResponseDto>> getAllEvents(){
        if(eventRepository.findAll().size() == 0){
            return new ObjectWrapper<>(404,"No events found!",null);
        }
        else{
            List<EventResponseDto> eventResponseDtoList = eventRepository.findAll().stream()
                    .map(eventMappers::eventToEventResponseDtoMapper)
                    .collect(Collectors.toList());
            return new ObjectWrapper<>(200,"Events found!", eventResponseDtoList);
        }
    }

    @Override
    public ObjectWrapper<EventResponseDto> getEventById(Long id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if(eventOptional.isPresent()){
            EventResponseDto responseEntity = eventMappers.eventToEventResponseDtoMapper(eventOptional.get());
            return new ObjectWrapper<>(200,"Event found!",responseEntity);
        }
        else{
            return new ObjectWrapper<>(404,"Event not found!",null);
        }
    }

    @Override
    public ObjectWrapper<EventResponseDto> updateEvent(EventUpdateDto eventUpdateDto) {
        Optional<Event> eventOptional = eventRepository.findById(eventUpdateDto.getId());
        if(!eventOptional.isPresent()) {
            return new ObjectWrapper<>(404, "Event not found!",null);
        }
        List<String> existingEventNames = eventRepository.getAllNames();
        Boolean isAnExistingName = existingEventNames.contains(eventUpdateDto.getName());
        Boolean isNotItself = !eventOptional.get().getName().equals(eventUpdateDto.getName());
        if(isAnExistingName && isNotItself) {
            return new ObjectWrapper<>(400,"Name already exist!",null);
        }
        String cannotBeUpdatedMessage = eventUpdateDto.canBeUpdated(eventRepository.getAll());
        if(!cannotBeUpdatedMessage.equals("OK")){
            return new ObjectWrapper<>(400, cannotBeUpdatedMessage, null);
        }
        eventOptional.get().setName(eventUpdateDto.getName());
        eventOptional.get().setStartDate(eventUpdateDto.getStartDate());
        eventOptional.get().setEndDate(eventUpdateDto.getEndDate());
        eventRepository.save(eventOptional.get());
        EventResponseDto responseEntity = eventMappers.eventToEventResponseDtoMapper(eventOptional.get());
        return new ObjectWrapper<>(200, "Event updated successfully!", responseEntity);
    }

    @Override
    public ObjectWrapper<EventResponseDto> deleteEventById(Long id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if(eventOptional.isPresent()){
            eventRepository.deleteById(id);
            EventResponseDto responseEntity = eventMappers.eventToEventResponseDtoMapper(eventOptional.get());
            return new ObjectWrapper<>(200,"Event deleted successfully!",responseEntity);
        }
        else{
            return new ObjectWrapper<>(404,"Event not found!",null);
        }
    }

    @Override
    public void deleteAllEvents(){
        eventRepository.deleteAll();
    }
}
