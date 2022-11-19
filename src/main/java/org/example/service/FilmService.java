package org.example.service;

import org.example.model.Film;
import org.example.repository.FilmDao;

import java.util.List;

public class FilmService {
    private final FilmDao repository;

    public FilmService(FilmDao repository) {
        this.repository = repository;
    }

    public List<Film> getAllFilms() {
        return repository.getAllFilms();
    }

    public void addFilm(Film film) {
        repository.addFilm(film);
    }

    public Film findFilmByTitle(String title) {
        return repository.findFilmByTitle(title);
    }

    public void updateFilm(int id, Film film) {
        repository.updateFilm(id, film);
    }

    public void deleteFilmById(int id) {
        repository.deleteFilmById(id);
    }
}
