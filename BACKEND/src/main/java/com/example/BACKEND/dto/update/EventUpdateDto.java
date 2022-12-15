package com.example.BACKEND.dto.update;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class EventUpdateDto {

    private Long id;
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;

    public EventUpdateDto() {
    }

    public EventUpdateDto(Long id, String name, LocalDateTime startDate, LocalDateTime endDate) {
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

    public Boolean isValid(){
        if(startDate.isBefore(LocalDateTime.now()) || startDate.isAfter(endDate)){
            return false;
        }
        else{
            return true;
        }
    }
}
