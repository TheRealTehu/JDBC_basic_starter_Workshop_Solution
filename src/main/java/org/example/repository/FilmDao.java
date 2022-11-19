package org.example.repository;

import org.example.model.Film;

import java.util.List;

public interface FilmDao {
    List<Film> getAllFilms();
    void addFilm(Film film);
    Film findFilmByTitle(String title);
    void updateFilm(int id, Film film);
    void deleteFilmById(int id);
}
