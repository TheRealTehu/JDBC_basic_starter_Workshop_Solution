package org.example.controller;

import org.example.model.Film;
import org.example.service.FilmService;

public class FilmController {
    private final FilmService service;

    public FilmController(FilmService service) {
        this.service = service;
    }

    public void listAllFilms() {
        service.getAllFilms().forEach(System.out::println);
    }

    public void addFilm(Film film) {
        service.addFilm(film);
        System.out.println("Film added");
    }

    public void wroteOutFilmByTitle(String title) {
        System.out.println(service.findFilmByTitle(title));
    }

    public void updateFilm(int id, Film film) {
        service.updateFilm(id, film);
        System.out.println("Film updated");
    }

    public void deleteFilmById(int id) {
        service.deleteFilmById(id);
        System.out.println("Film deleted");
    }
}
