package com.eventhub.eventhub_backend.repository;

import com.eventhub.eventhub_backend.model.Event;
import com.eventhub.eventhub_backend.model.Reservation;
import com.eventhub.eventhub_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);
    List<Reservation> findByEvent(Event event);
    boolean existsByUserAndEvent(User user, Event event);
}
