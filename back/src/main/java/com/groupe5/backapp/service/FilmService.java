package com.groupe5.backapp.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.groupe5.backapp.modele.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

@Service
public class FilmService {

    private final RestClient restClient;

    public FilmService() {

        this.restClient = RestClient.builder()
                .baseUrl("https://ghibliapi.vercel.app")
                .build();
    }

    public ResponseEntity<Film[]> callExternalApiAllFilms(String endpoint) {
        try{
            Film[] films = restClient
                    .get()
                    .uri(endpoint)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toEntity(Film[].class)
                    .getBody();

            return new ResponseEntity<>(films, HttpStatus.OK);

        }catch (HttpStatusCodeException e) {
            return ResponseEntity
                    .status(e.getStatusCode())
                    .body(null);
        }

    }

    public ResponseEntity<Film> callExternalApiOneFilm(String endpoint) {
        try {
            Film film = restClient
                    .get()
                    .uri(endpoint)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .body(Film.class);

            return ResponseEntity.ok(film);

        } catch (HttpStatusCodeException e) {

            return ResponseEntity
                    .status(e.getStatusCode())
                    .body(null);
        }

    }



    //GET & SET



}
