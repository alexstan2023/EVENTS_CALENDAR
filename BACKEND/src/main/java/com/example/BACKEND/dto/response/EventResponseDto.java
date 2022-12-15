package com.example.BACKEND.dto.response;

import java.time.LocalDateTime;

public class EventResponseDto {

    private Long id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public EventResponseDto() {
    }

    public EventResponseDto(Long id, String name, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
