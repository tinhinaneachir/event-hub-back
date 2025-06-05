package com.eventhub.eventhub_backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

    private Long id;
    private String title;
    private String description;
    private String Location;
    private LocalDateTime localDateTime;
    private int totalSeats;
    private int reservedSeats;

}
