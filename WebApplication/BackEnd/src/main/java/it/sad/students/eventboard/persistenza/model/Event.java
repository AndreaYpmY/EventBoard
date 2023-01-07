package it.sad.students.eventboard.persistenza.model;

import java.time.LocalDate;

public class Event {
    private Long id = null;
    private LocalDate date;
    private String title;
    private Double price;
    private Boolean soldOut;
    private String urlPoster;
    private String description;

    //chiavi esterne
    private Long eventType;
    private Long position;
    private Long organizer;

    public Event() {
    }

    public Event(Long id, LocalDate date,String title, Double price, Boolean available, String urlPoster, Long eventType, Long luogo, Long person, String description) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.price = price;
        this.soldOut = available;
        this.urlPoster = urlPoster;
        this.eventType = eventType;
        this.position = luogo;
        this.organizer = person;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getSoldOut() {
        return soldOut;
    }

    public void setSoldOut(Boolean soldOut) {
        this.soldOut = soldOut;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    public Long getEventType() {
        return eventType;
    }

    public void setEventType(Long eventType) {
        this.eventType = eventType;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public Long getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Long organizer) {
        this.organizer = organizer;
    }
}
