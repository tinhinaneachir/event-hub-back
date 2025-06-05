package com.eventhub.eventhub_backend.repository;

import com.eventhub.eventhub_backend.model.Event;
import com.eventhub.eventhub_backend.model.Reservation;
import com.eventhub.eventhub_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);
    List<Event> findByEvent(Event event);
    boolean existsByUserAndEvent(User user, Event event);
}
