package com.example.BACKEND.utils;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class TimeInfo {

    public List<Pair<Integer,Integer>> nrOfDaysPerMonth = new ArrayList<>();
    public List<Pair<Integer, Month>> nameOfMonths = new ArrayList<>();

    public void init(){
        nrOfDaysPerMonth.add(Pair.of(1,30));
        nrOfDaysPerMonth.add(Pair.of(2,28));
        nrOfDaysPerMonth.add(Pair.of(3,31));
        nrOfDaysPerMonth.add(Pair.of(4,30));
        nrOfDaysPerMonth.add(Pair.of(5,31));
        nrOfDaysPerMonth.add(Pair.of(6,30));
        nrOfDaysPerMonth.add(Pair.of(7,31));
        nrOfDaysPerMonth.add(Pair.of(8,31));
        nrOfDaysPerMonth.add(Pair.of(9,30));
        nrOfDaysPerMonth.add(Pair.of(10,31));
        nrOfDaysPerMonth.add(Pair.of(11,30));
        nrOfDaysPerMonth.add(Pair.of(12,31));

        Integer i=1;

        for(Month j : Month.values()){
            nameOfMonths.add(Pair.of(i,j));
            i = i + 1;
        }
    }

    public Integer getNrOfDays(Integer month,Integer year){
        init();
        if(month==1 && year % 4 ==0){
            return 29;
        }
        else{
            return nrOfDaysPerMonth.get(month-1).getSecond();
        }
    }

    public List<Integer> extractDataValues(String data){
        Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}");
        if(!data.matches(pattern.pattern())){
            System.out.println(data);
            return null;
        }
        else {
            String[] elements = data.split(" ");
            String[] dateElements = elements[0].split("-");
            String[] timeElements = elements[1].split(":");
            Integer year = Integer.parseInt(dateElements[0]);
            Integer month = Integer.parseInt(dateElements[1]);
            Integer day = Integer.parseInt(dateElements[2]);
            Integer hour = Integer.parseInt(timeElements[0]);
            Integer minute = Integer.parseInt(timeElements[1]);
            Integer second = Integer.parseInt(timeElements[2]);
            List<Integer> dates = new ArrayList<>();
            dates.add(year);
            dates.add(month);
            dates.add(day);
            dates.add(hour);
            dates.add(minute);
            dates.add(second);
            return dates;
        }
    }

    public Boolean isValid(List<Integer> l){
        if(l.get(5) <0 || l.get(5) >59){
            return false;
        }
        if(l.get(4) <0 || l.get(4) >59){
            return false;
        }
        if(l.get(3)<0 || l.get(3)>23){
            return false;
        }
        if(l.get(2)<1 || l.get(2)>getNrOfDays(l.get(1),l.get(0))){
            return false;
        }
        if(l.get(1)<1 || l.get(1)>12){
            return false;
        }
        return true;
    }

    public Boolean isValid(String data){
        Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}");
        if(!data.matches(pattern.pattern())){
            return false;
        }
        else if(!isValid(extractDataValues(data))){
            return false;
        }
        return true;
   }

   public Boolean isBefore(String data1, String data2){
        List<Integer> dates1 = extractDataValues(data1);
        List<Integer> dates2 = extractDataValues(data2);
        if(dates1.get(0)<dates2.get(0)){
            return true;
        }
        else if(dates1.get(0).equals(dates2.get(0)) &&
                dates1.get(1)<dates2.get(1)){
            return true;
        }
        else if(dates1.get(0).equals(dates2.get(0)) &&
                dates1.get(1).equals(dates2.get(1)) &&
                dates1.get(2) < dates2.get(2)){
            return true;
        }
        else if(dates1.get(0).equals(dates2.get(0)) &&
                dates1.get(1).equals(dates2.get(1)) &&
                dates1.get(2).equals(dates2.get(2)) &&
                dates1.get(3) < dates2.get(3)){
            return true;
        }
        else if(dates1.get(0).equals(dates2.get(0)) &&
                dates1.get(1).equals(dates2.get(1)) &&
                dates1.get(2).equals(dates2.get(2)) &&
                dates1.get(3).equals(dates2.get(3)) &&
                dates1.get(4) < dates2.get(4)){
            return true;
        }
        else if(dates1.get(0).equals(dates2.get(0)) &&
                dates1.get(1).equals(dates2.get(1)) &&
                dates1.get(2).equals(dates2.get(2)) &&
                dates1.get(3).equals(dates2.get(3)) &&
                dates1.get(4).equals(dates2.get(4)) &&
                dates1.get(5) <= dates2.get(5)){
            return true;
        }
        else{
            return false;
        }
   }

}
