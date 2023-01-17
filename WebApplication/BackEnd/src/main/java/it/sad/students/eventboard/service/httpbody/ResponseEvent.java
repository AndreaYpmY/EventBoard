package it.sad.students.eventboard.service.httpbody;

import it.sad.students.eventboard.persistenza.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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

/*card eventi:
immagine,
id, titolo
data, ora, luogo
Nome organizzatore
 */

/*full details dell'evento:
    Event event;
    List<Comment> commentList;
    List<Like> likeList;
    List<Partecipation> partecipationList;
    List<Review> reviewList;

 */