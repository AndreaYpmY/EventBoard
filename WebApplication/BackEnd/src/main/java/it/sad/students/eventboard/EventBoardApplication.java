package it.sad.students.eventboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventBoardApplication {

    public static void main(String[] args) {
        System.out.println("ciao a tutti, siete in dev, aggiornato!!!");
        SpringApplication.run(EventBoardApplication.class, args);
    }

}
