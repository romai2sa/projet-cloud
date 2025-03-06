package com.groupe5.backapp.controleur;

import com.groupe5.backapp.modele.Film;
import com.groupe5.backapp.service.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpStatusCodeException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ControleurTest {

    @Mock
    private FilmService filmService;

    @InjectMocks
    private Controleur controleur;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controleur).build();
    }

    private Film createFilm(String id, String title) {
        Film film = new Film();
        film.setId(id);
        film.setTitle(title);
        film.setOriginal_title("Titre original " + title);
        film.setOriginal_title_romanised("Romanized " + title);
        film.setDirector("Directeur " + title);
        film.setProducer("Producteur " + title);
        film.setRelease_date("2024-12-01");
        film.setRunning_time("120");
        film.setRt_score("80");
        return film;
    }

    @Test
    public void testGetAllFilms_Success() throws Exception {
        // Création de films
        Film[] films = {
                createFilm("1", "Film1"),
                createFilm("2", "Film2")
        };

        when(filmService.callExternalApiAllFilms("/films")).thenReturn(ResponseEntity.ok(films));

        mockMvc.perform(get("/films")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                //FILM 1
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].title").value("Film1"))
                .andExpect(jsonPath("$[0].original_title").value("Titre original Film1"))
                .andExpect(jsonPath("$[0].director").value("Directeur Film1"))
                .andExpect(jsonPath("$[0].producer").value("Producteur Film1"))
                .andExpect(jsonPath("$[0].release_date").value("2024-12-01"))
                .andExpect(jsonPath("$[0].running_time").value("120"))
                .andExpect(jsonPath("$[0].rt_score").value("80"))
                //FILM 2
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].title").value("Film2"))
                .andExpect(jsonPath("$[1].original_title").value("Titre original Film2"))
                .andExpect(jsonPath("$[1].director").value("Directeur Film2"))
                .andExpect(jsonPath("$[1].producer").value("Producteur Film2"))
                .andExpect(jsonPath("$[1].release_date").value("2024-12-01"))
                .andExpect(jsonPath("$[1].running_time").value("120"))
                .andExpect(jsonPath("$[1].rt_score").value("80"));

        // Vérification que FilmService a été appelé une seule fois
        verify(filmService, times(1)).callExternalApiAllFilms("/films");
    }

    @Test
    public void testGetAllFilms_EmptyList() throws Exception {
        Film[] films = {};

        when(filmService.callExternalApiAllFilms("/films")).thenReturn(ResponseEntity.ok(films));

        mockMvc.perform(get("/films").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0)); // Vérifie que la liste est vide

        verify(filmService, times(1)).callExternalApiAllFilms("/films");
    }

    @Test
    public void testGetAllFilms_BadRequest() throws Exception {
        when(filmService.callExternalApiAllFilms("/films"))
                .thenThrow(new HttpStatusCodeException(HttpStatus.BAD_REQUEST) {});

        mockMvc.perform(get("/films").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(filmService, times(1)).callExternalApiAllFilms("/films");
    }

    @Test
    public void testGetAllFilms_NotFound() throws Exception {
        when(filmService.callExternalApiAllFilms("/films"))
                .thenThrow(new HttpStatusCodeException(HttpStatus.NOT_FOUND) {});

        mockMvc.perform(get("/films").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(filmService, times(1)).callExternalApiAllFilms("/films");
    }

    @Test
    public void testGetAllFilms_Not_Implemented() throws Exception {
        when(filmService.callExternalApiAllFilms("/films"))
                .thenThrow(new HttpStatusCodeException(HttpStatus.NOT_IMPLEMENTED) {});

        mockMvc.perform(get("/films").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotImplemented());

        verify(filmService, times(1)).callExternalApiAllFilms("/films");
    }



    @Test
    public void testGetFilm_Success() throws Exception {

        Film film = createFilm("1", "Film1");
        when(filmService.callExternalApiOneFilm("/films/1")).thenReturn(ResponseEntity.ok(film));

        mockMvc.perform(get("/films/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.title").value("Film1"))
                .andExpect(jsonPath("$.original_title").value("Titre original Film1"))
                .andExpect(jsonPath("$.director").value("Directeur Film1"))
                .andExpect(jsonPath("$.producer").value("Producteur Film1"))
                .andExpect(jsonPath("$.release_date").value("2024-12-01"))
                .andExpect(jsonPath("$.running_time").value("120"))
                .andExpect(jsonPath("$.rt_score").value("80"));

        verify(filmService, times(1)).callExternalApiOneFilm("/films/1");
    }

    @Test
    public void testGetFilm_NotFound() throws Exception {

        when(filmService.callExternalApiOneFilm("/films/129382986621")).thenThrow(new HttpStatusCodeException(HttpStatus.NOT_FOUND) {});

        mockMvc.perform(get("/films/129382986621")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(filmService, times(1)).callExternalApiOneFilm("/films/129382986621");
    }

    @Test
    public void testGetFilm_BadRequest() throws Exception {

        when(filmService.callExternalApiOneFilm("/films/mmmmmmmmm")).thenThrow(new HttpStatusCodeException(HttpStatus.BAD_REQUEST) {});

        mockMvc.perform(get("/films/mmmmmmmmm", "d")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(filmService, times(1)).callExternalApiOneFilm("/films/mmmmmmmmm");
    }



}