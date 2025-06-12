package com.eventhub.eventhub_backend.controller;


import com.eventhub.eventhub_backend.model.Event;
import com.eventhub.eventhub_backend.repository.EventRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EventController {

    private final EventRespository eventRespository;

    @GetMapping
    public List<Event> getAllEvents(){
        return eventRespository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id){
        Optional<Event> event = eventRespository.findById(id);
        return event.map(ResponseEntity :: ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event){
        return eventRespository.save(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event updateEvent ){
        return eventRespository.findById(id).map(event -> {
            event.setId(updateEvent.getId());
            event.setDescription(updateEvent.getDescription());
            event.setLocation(updateEvent.getLocation());
            event.setReservation(updateEvent.getReservation());
            event.setTotalSeats(updateEvent.getTotalSeats());
            event.setLocalDateTime(updateEvent.getLocalDateTime());
            return ResponseEntity.ok(eventRespository.save(event));
        }).orElseGet(()-> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Event> deleteEvent(@PathVariable Long id){
        if(eventRespository.existsById(id)){
            eventRespository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }




    public EventController(EventRespository eventRespository) {
        this.eventRespository = eventRespository;
    }
}
