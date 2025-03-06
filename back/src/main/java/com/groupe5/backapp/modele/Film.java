package com.groupe5.backapp.modele;

import java.util.List;

public class Film {

    private String id;
    private String title;
    private String original_title;
    private String original_title_romanised;
    private String director;
    private String producer;
    private String release_date;
    private String running_time;
    private String rt_score;
    private String image;
    private String movie_banner;
    private String description;
    private List<String> people;
    private List<String> species;
    private List<String> locations;
    private List<String> vehicles;
    private String url;

    //constructor


    public Film(String id, String title, String original_title, String original_title_romanised, String director, String producer, String release_date, String running_time, String rt_score, String image, String movie_banner, String description, List<String> people, List<String> species, List<String> locations, List<String> vehicles, String url) {
        this.id = id;
        this.title = title;
        this.original_title = original_title;
        this.original_title_romanised = original_title_romanised;
        this.director = director;
        this.producer = producer;
        this.release_date = release_date;
        this.running_time = running_time;
        this.rt_score = rt_score;
        this.image = image;
        this.movie_banner = movie_banner;
        this.description = description;
        this.people = people;
        this.species = species;
        this.locations = locations;
        this.vehicles = vehicles;
        this.url = url;
    }

    public Film() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_title_romanised() {
        return original_title_romanised;
    }

    public void setOriginal_title_romanised(String original_title_romanised) {
        this.original_title_romanised = original_title_romanised;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getRunning_time() {
        return running_time;
    }

    public void setRunning_time(String running_time) {
        this.running_time = running_time;
    }

    public String getRt_score() {
        return rt_score;
    }

    public void setRt_score(String rt_score) {
        this.rt_score = rt_score;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMovie_banner() {
        return movie_banner;
    }

    public void setMovie_banner(String movie_banner) {
        this.movie_banner = movie_banner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPeople() {
        return people;
    }

    public void setPeople(List<String> people) {
        this.people = people;
    }

    public List<String> getSpecies() {
        return species;
    }

    public void setSpecies(List<String> species) {
        this.species = species;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<String> vehicles) {
        this.vehicles = vehicles;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
