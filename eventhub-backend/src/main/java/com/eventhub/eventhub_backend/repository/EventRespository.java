package com.eventhub.eventhub_backend.repository;

import com.eventhub.eventhub_backend.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRespository extends JpaRepository<Event, Long> {
}
