package com.example.BACKEND.dto.update;

import com.example.BACKEND.dto.insert.EventInsertDto;
import com.example.BACKEND.model.Event;
import com.example.BACKEND.utils.TimeInfo;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventUpdateDto {

    private Long id;
    private String name;

    private String startDate;
    private String endDate;

    public EventUpdateDto() {
    }

    public EventUpdateDto(Long id, String name, String startDate, String endDate) {
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

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String formNowDate(){
        List<Integer> dates = new ArrayList<>();
        List<String> stringDates = new ArrayList<>();

        dates.add(LocalDateTime.now().getYear());
        dates.add(LocalDateTime.now().getMonthValue());
        dates.add(LocalDateTime.now().getDayOfMonth());
        dates.add(LocalDateTime.now().getHour());
        dates.add(LocalDateTime.now().getMinute());
        dates.add(LocalDateTime.now().getSecond());

        for(int i=0; i<dates.size(); i++){
            if(dates.get(i)<10){
                stringDates.add("0" + dates.get(i));
            }
            else{
                stringDates.add(dates.get(i).toString());
            }
        }

        return stringDates.get(0) + "-" + stringDates.get(1) + "-" + stringDates.get(2) +" " +
                stringDates.get(3) + ":" + stringDates.get(4) + ":" + stringDates.get(5);
    }

    public String canBeUpdated(List<Event> eventList){
        if (!new TimeInfo().isValid(startDate)) {
            return "Invalid start date format.The valid format is yyyy-MM-dd HH:mm!";
        }
        if (!new TimeInfo().isValid(endDate)){
            return "Invalid end date format.The valid format is yyyy-MM-dd HH:mm!";
        }
        if(!new TimeInfo().isBefore(startDate,endDate)){
            return "Invalid date!Start date is before end date!";
        }
        if(!new TimeInfo().isBefore(formNowDate(),startDate)){
            return "Invalid date!Start date passed!";
        }
        for(Integer i=0; i<eventList.size(); i++){
            if(!eventList.get(i).getName().equals(name)) {
                Boolean areDisjunct = new TimeInfo().isBefore(eventList.get(i).getEndDate(), startDate) ||
                        new TimeInfo().isBefore(endDate, eventList.get(i).getStartDate());
                if (!areDisjunct) {
                    String error = "Wrong planning!Event intersects with event " + eventList.get(i).getName() + "\n";
                    if (!new TimeInfo().isBefore(eventList.get(i).getEndDate(), startDate)) {
                        error += "Event " + eventList.get(i).getName()
                                + " end date: " + eventList.get(i).getEndDate() + "\n";
                        error += "Your event start date: " + startDate;
                    } else {
                        error += "Your event end date: " + endDate + "\n";
                        error += "Event " + eventList.get(i).getName()
                                + " start date: " + eventList.get(i).getStartDate();
                    }
                    return error;
                }
            }
        }
        return "OK";
    }
}
