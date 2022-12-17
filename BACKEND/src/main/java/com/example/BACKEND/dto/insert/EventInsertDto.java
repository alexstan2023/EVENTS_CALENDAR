package com.example.BACKEND.dto.insert;

import com.example.BACKEND.model.Event;
import com.example.BACKEND.utils.TimeInfo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventInsertDto implements Serializable {

    private String name;
    private String startDate;
    private String endDate;

    public EventInsertDto() {
    }

    public EventInsertDto(String name, String startDate, String endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String canBeCreated(List<Event> eventList){

        if (!new TimeInfo().isValid(startDate)) {
            return "Invalid start date format.The valid format is yyyy-MM-dd HH:mm:ss!";
        }
        if (!new TimeInfo().isValid(endDate)){
            return "Invalid end date format.The valid format is yyyy-MM-dd HH:mm:ss!";
        }

        if(!new TimeInfo().isBefore(startDate,endDate)){
            return "Invalid date!Start date is before end date!";
        }

        if(!new TimeInfo().isBefore(formNowDate(),startDate)){
            return "Invalid date!Start date passed!";
        }
        for (Event event : eventList) {
            boolean areDisjoint = new TimeInfo().isBefore(event.getEndDate(), startDate) ||
                    new TimeInfo().isBefore(endDate, event.getStartDate());
            if (!areDisjoint) {
                String error = "Wrong planning!Event intersects with event " + event.getName() + "\n";
                if (!new TimeInfo().isBefore(event.getEndDate(), startDate)) {
                    error += "Event " + event.getName() + " end date: " + event.getEndDate() + "\n";
                    error += "Your event start date: " + startDate;
                } else {
                    error += "Your event end date: " + endDate + "\n";
                    error += "Event " + event.getName() + " start date: " + event.getStartDate();
                }
                return error;
            }
        }
        return "OK";
    }
}
