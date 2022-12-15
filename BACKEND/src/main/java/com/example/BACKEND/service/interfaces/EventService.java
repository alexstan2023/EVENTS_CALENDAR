package com.example.BACKEND.service.interfaces;

import com.example.BACKEND.ObjectWrapper;
import com.example.BACKEND.dto.insert.EventInsertDto;
import com.example.BACKEND.dto.response.EventResponseDto;
import com.example.BACKEND.dto.update.EventUpdateDto;

public interface EventService {

    ObjectWrapper<EventResponseDto> addEvent(EventInsertDto eventInsertDto);
    ObjectWrapper<EventResponseDto> getEventById(Long id);
    ObjectWrapper<EventResponseDto> updateEvent(EventUpdateDto eventUpdateDto);
    ObjectWrapper<EventResponseDto> deleteEventById(Long id);

}
