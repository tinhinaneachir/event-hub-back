package com.eventhub.eventhub_backend.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }


}
