package it.sad.students.eventboard.controller.api;

import it.sad.students.eventboard.persistenza.model.Comment;
import it.sad.students.eventboard.persistenza.model.Person;
import it.sad.students.eventboard.persistenza.model.Review;
import it.sad.students.eventboard.service.InteractionService;
import it.sad.students.eventboard.service.RequestPersonEvent;
import it.sad.students.eventboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final InteractionService interactionService;

   @RequestMapping("/api/user/{username}")
    public ResponseEntity<Person> getPerson(@PathVariable String username){
        return ResponseEntity.ok(userService.getPerson(username));
    }

    // TODO: 06/01/2023 Rivisionare path scritti

    @PostMapping("/api/like")
    public ResponseEntity setLike(@RequestBody RequestPersonEvent pe/*Long person, Long event*/){
        System.out.println(pe.getPerson());
        if(interactionService.setLike(pe.getPerson(),pe.getEvent()))
            return ResponseEntity.ok("");
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/partecipation")
    public ResponseEntity setPartecipation(@RequestBody RequestPersonEvent pe){
        if(interactionService.setPartecipation(pe.getPerson(), pe.getEvent()))
            return ResponseEntity.ok("");
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/api/comment/add")
    public ResponseEntity addComment(@RequestBody Comment comment,@RequestHeader (name="Authorization") String token){
       return interactionService.addComment(comment,token);
    }

    @PostMapping("api/review/add")
    public ResponseEntity<Boolean> addReviews(@RequestBody Review review){
        if(interactionService.addReview(review))
            return ResponseEntity.ok(true);
        return ResponseEntity.ok(false);
    }


    // TODO: 06/01/2023 Eliminare commenti e review tramite i vari id

    @RequestMapping(value = "api/comment/delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> removeComment(@PathVariable Long id,@RequestHeader (name="Authorization") String token){

        if(interactionService.deleteComment(id,token))
            return ResponseEntity.ok(true);
        return ResponseEntity.ok(false);
    }

    @RequestMapping(value = "api/review/delete",method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> removeReviews(@RequestBody RequestPersonEvent pe,@RequestHeader (name="Authorization") String token){

        if(interactionService.deleteReview(pe.getPerson(),pe.getEvent(),token))
            return ResponseEntity.ok(true);
        return ResponseEntity.ok(false);
    }




}
