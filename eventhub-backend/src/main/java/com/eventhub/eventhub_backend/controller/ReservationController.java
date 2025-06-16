package com.eventhub.eventhub_backend.controller;


import com.eventhub.eventhub_backend.model.Event;
import com.eventhub.eventhub_backend.model.Reservation;
import com.eventhub.eventhub_backend.model.User;
import com.eventhub.eventhub_backend.repository.EventRespository;
import com.eventhub.eventhub_backend.repository.ReservationRepository;
import com.eventhub.eventhub_backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final UserRepository userRepository;
    private final EventRespository eventRespository;
    private final ReservationRepository reservationRepository;

    public ReservationController(UserRepository userRepository,
                                 EventRespository eventRespository,
                                 ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.eventRespository = eventRespository;
        this.reservationRepository = reservationRepository;
    }

    @PostMapping
    public ResponseEntity<?> reservEvent(@PathVariable Long eventId, Principal principal){
        Optional<User> user = userRepository.findByEmail(principal.getName());
        Optional<Event> event = eventRespository.findById(eventId);

        if(user.isPresent() && event.isPresent()){
            Reservation reservation = new Reservation();
            reservation.setUser(user.get());
            reservation.setEvent(event.get());
            reservation.setLocalDateTime(LocalDateTime.now());

            reservationRepository.save(reservation);
            return ResponseEntity.ok("REservation réussi!");
        }
        return  ResponseEntity.badRequest().body("Utilisateur ou evenement non trouvable");
    }


    @GetMapping("/my")
    public ResponseEntity<List<Reservation>> getMyReservation(Principal principal){
        Optional<User> user = userRepository.findByEmail(principal.getName());

        if(user.isPresent()){
            List<Reservation> reservations = reservationRepository.findByUser(user.get());
            return  ResponseEntity.ok(reservations);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable Long id, Principal principal ){
        Optional<Reservation> reservation = reservationRepository.findById(id);

        if(reservation.isPresent() && reservation.get().getUser().getEmail().equals(principal.getName())){
            reservationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(403).build();

    }


}
