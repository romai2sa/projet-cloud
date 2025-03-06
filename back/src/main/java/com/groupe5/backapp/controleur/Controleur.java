package com.groupe5.backapp.controleur;

import com.groupe5.backapp.modele.Film;
import com.groupe5.backapp.service.FilmService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controleur {

    FilmService filmService;

    public Controleur() {
        this.filmService = new FilmService();
    }

    @Hidden
    @GetMapping("/")
    public ResponseEntity<String> getAccueil(){
        return ResponseEntity.ok().body("Bienvenue dans notre BACK !");
    }


    @Operation(summary = "Récupérer tous les films", description = "Retourne une liste complète de films depuis l'API externe")
    @ApiResponse(responseCode = "200", description = "Liste de films récupérée avec succès")
    @ApiResponse(responseCode = "400", description = "Requête incorrecte")
    @ApiResponse(responseCode = "403", description = "Requête non authorisée")
    @ApiResponse(responseCode = "404", description = "Liste de films non trouvée")
    @ApiResponse(responseCode = "501", description = "Requête non implémentée")
    @GetMapping("/films")
    public ResponseEntity<Film[]> getAllFilms(){
        try {
            return filmService.callExternalApiAllFilms("/films");
        } catch (HttpStatusCodeException e) {
            if(e.getStatusCode() == HttpStatus.NOT_FOUND)
                return ResponseEntity.notFound().build();
            else if (e.getStatusCode() == HttpStatus.BAD_REQUEST)
                return ResponseEntity.badRequest().build();
            else
                return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED) .build();
            }
        }


    @Operation(summary = "Récupérer un films", description = "Retourne les détails d'un film spécifique par son identifiant depuis l'API externe")
    @ApiResponse(responseCode = "200", description = "Film récupéré avec succès")
    @ApiResponse(responseCode = "400", description = "Requête incorrecte")
    @ApiResponse(responseCode = "403", description = "Requête non authorisée")
    @ApiResponse(responseCode = "404", description = "Film non trouvé")
    @ApiResponse(responseCode = "501", description = "Requête non implémentée")
    @GetMapping("/films/{id}")
    public ResponseEntity<Film> getFilm(@PathVariable String id){
        try {
            return filmService.callExternalApiOneFilm("/films/" + id);
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return ResponseEntity.notFound().build();
            } else if (e.getStatusCode() == HttpStatus.BAD_REQUEST){
                return ResponseEntity.badRequest().build();
            } else
                return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
        }
    }
}
