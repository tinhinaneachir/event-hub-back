package com.eventhub.eventhub_backend.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDateTime localDateTime;

    @ManyToMany
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToMany
    @JoinColumn(name = "event_id")
    private Event event;

}
