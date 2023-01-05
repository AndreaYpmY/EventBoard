package it.sad.students.eventboard.controller.api;

import it.sad.students.eventboard.persistenza.model.Event;
import it.sad.students.eventboard.security.auth.AuthenticationRequest;
import it.sad.students.eventboard.security.auth.AuthenticationResponse;
import it.sad.students.eventboard.security.auth.AuthenticationService;
import it.sad.students.eventboard.security.auth.RegisterRequest;
import it.sad.students.eventboard.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final AuthenticationService service;
    private final EventService eventService;

    @RequestMapping ("/api/home")
    public Iterable<Event> getHome(){
        return eventService.getAllEvents();
    }
    // TODO: 05/01/2023 carichiamo tutti?senza limiti? 

    @PreAuthorize("hasRole('USER')")
    @RequestMapping("/api/auth/home/{username}")
    public ResponseEntity<Iterable<Event>> getHomeWithPreferences(@PathVariable String username){
        return ResponseEntity.ok(eventService.getPreferredEvents(username));
    }

}
