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
    public ResponseEntity addReviews(@RequestBody Review review,@RequestHeader (name="Authorization") String token){
        return interactionService.addReview(review,token);
    }


    @RequestMapping(value = "api/comment/delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity deleteComment(@PathVariable Long id,@RequestHeader (name="Authorization") String token){
        return interactionService.deleteComment(id,token);

    }

    @RequestMapping(value = "api/review/delete",method = RequestMethod.DELETE)
    public ResponseEntity deleteReviews(@RequestBody RequestPersonEvent pe,@RequestHeader (name="Authorization") String token){
        return interactionService.deleteReview(pe.getPerson(),pe.getEvent(),token);
    }

    @RequestMapping(value="api/user/edit",method = RequestMethod.PUT)
    public ResponseEntity editUser(@RequestBody Person person,@RequestHeader (name="Authorization") String token){
       return userService.editUser(person,token);
    }


    // TODO: 08/01/2023 Decidere se fare rimozione a cascata dal database
    @RequestMapping(value="api/user/delete",method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@RequestBody Long id,@RequestHeader (name="Authorization") String token){
        return userService.disableUser(id,token);
    }
    @RequestMapping(value="api/user/enable",method = RequestMethod.POST)
    public ResponseEntity enableUser(@RequestBody Long id,@RequestHeader (name="Authorization") String token){
        return userService.enableUser(id,token);
    }









}
