package com.example.BACKEND.repository;

import com.example.BACKEND.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {

    @Query(value="select * from event",nativeQuery = true)
    List<Event> getAll();

    @Query(value="select name from event",nativeQuery = true)
    List<String> getAllNames();

}
