package it.sad.students.eventboard.service.custom.response;

import it.sad.students.eventboard.persistenza.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ResponseEvent {

    private Long id;
    private LocalDateTime date;
    private String title;
    private String urlPoster;
    private String eventType;
    private Position position;
    private String organizer;

}

