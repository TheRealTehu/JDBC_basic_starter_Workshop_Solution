package org.example;

import org.example.controller.ActorController;
import org.example.controller.FilmController;
import org.example.model.Actor;
import org.example.model.Film;
import org.example.model.Rating;
import org.example.repository.ActorDao;
import org.example.repository.ActorDaoJdbc;
import org.example.repository.FilmDao;
import org.example.repository.FilmDaoJdbc;
import org.example.service.ActorService;
import org.example.service.FilmService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    final static ActorDao actorRepository = new ActorDaoJdbc();
    final static ActorService actorService = new ActorService(actorRepository);
    final static ActorController actorController = new ActorController(actorService);

    final static FilmDao filmRepository = new FilmDaoJdbc();

    final static FilmService filmService = new FilmService(filmRepository);

    final static FilmController filmController = new FilmController(filmService);
    public static void main(String[] args) {
        System.out.println("Hello world!");


        mainMenu();
    }

    private static void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose model");
        System.out.println("1. Actor");
        System.out.println("2. Film");
        System.out.println("3. Exit");
        String input = scanner.nextLine();

        switch (input){
            case "1": actorMenu(); break;
            case "2": filmMenu(); break;
            default:;
        }
    }

    private static void filmMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose option!");
        System.out.println("1. List all films!");
        System.out.println("2. Get film by title!");
        System.out.println("3. Add pre made film!");
        System.out.println("4. Update film!");
        System.out.println("5. Delete film!");
        System.out.println("6. Exit");

        String input = scanner.nextLine();

        switch (input){
            case "1": filmController.listAllFilms(); filmMenu(); break;
            case "2": getFilmByTitle(); filmMenu(); break;
            case "3": filmController.addFilm(new Film(1, "NEW MOVIE", "THIS IS A NEW MOVIE",
                    2022, 1, 2, 1.00, 100, 4.99, Rating.PG,
                    Date.valueOf(LocalDate.now()), List.of("Play", "Something"))); filmMenu(); break;
            case "4": updateFilm(); filmMenu(); break;
            case "5": deleteFilmById(); filmMenu(); break;
            default:;
        }
    }

    private static void deleteFilmById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which movie do you want to delete? (By Id!)");
        int id = scanner.nextInt();

        filmController.deleteFilmById(id);
    }

    private static void updateFilm() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which movie do you want to change? (By Id!)");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Give the new title!");
        String title = scanner.nextLine();

        System.out.println("Give the new description!");
        String description = scanner.nextLine();

        System.out.println("Give the new release year!");
        int year = Integer.parseInt(scanner.nextLine());

        System.out.println("Give the new language id!");
        int languageId = Integer.parseInt(scanner.nextLine());

        System.out.println("Give the new rental duration!");
        int rentalDuration = Integer.parseInt(scanner.nextLine());

        System.out.println("Give the new rental rate!");
        double rentalRate = Double.parseDouble(scanner.nextLine());

        System.out.println("Give the new length id!");
        int length = Integer.parseInt(scanner.nextLine());

        System.out.println("Give the new replacement cost!!");
        double replacementCost = Double.parseDouble(scanner.nextLine());

        System.out.println("Give the new Rating!");
        Rating rating = Rating.valueOf(scanner.nextLine());

        Date date = Date.valueOf(LocalDate.now());

        List<String> special = List.of("Addimg", "New", "Stuff");

        Film newFilm = new Film(1, title, description, year, languageId, rentalDuration, rentalRate, length, replacementCost, rating, date, special);

        filmController.updateFilm(id, newFilm);
    }

    private static void getFilmByTitle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What movie do you want to find?");
        String input = scanner.nextLine();

        filmController.wroteOutFilmByTitle(input);
    }

    private static void actorMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose option!");
        System.out.println("1. List all actors!");
        System.out.println("2. Get actor by name!");
        System.out.println("3. Add pre made actor!");
        System.out.println("4. Change actors name!");
        System.out.println("5. Delete actor!");
        System.out.println("6. Exit");

        String input = scanner.nextLine();

        switch (input){
            case "1": actorController.listAllActors(); actorMenu(); break;
            case "2": getActorByName(); actorMenu(); break;
            case "3": actorController.addActor(new Actor(1,"NEW", "ACTOR")); actorMenu(); break;
            case "4": changeActor(); actorMenu(); break;
            case "5": deleteActorById(); actorMenu(); break;
            default:;
        }
    }

    private static void deleteActorById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Who do you want to delete? (By Id!)");
        int id = scanner.nextInt();

        actorController.deleteActorById(id);
    }

    private static void changeActor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Who do you want to change? (By Id!)");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Give the new name!");
        String name = scanner.nextLine();
        String[] nameSplit = name.split(" ");
        Actor newActor = new Actor(id, nameSplit[0], nameSplit[1]);

        actorController.updateActor(id, newActor);
    }

    private static void getActorByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Who do you want to find?");
        String input = scanner.nextLine();

        actorController.writeOutActorByName(input);
    }
}