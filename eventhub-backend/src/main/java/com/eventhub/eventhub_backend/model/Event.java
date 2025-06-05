package com.eventhub.eventhub_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;
    private String location;
    private LocalDateTime localDateTime;
    private int totalSeats;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    Set<Reservation> reservation;



}
