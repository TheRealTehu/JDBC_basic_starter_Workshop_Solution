package org.example;

import org.example.controller.ActorController;
import org.example.model.Actor;
import org.example.repository.ActorDao;
import org.example.repository.ActorDaoJdbc;
import org.example.service.ActorService;

import java.util.Scanner;

public class Main {

    final static ActorDao repository = new ActorDaoJdbc();
    final static ActorService service = new ActorService(repository);
    final static ActorController controller = new ActorController(service);
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
            case "1": controller.listAllActors(); actorMenu(); break;
            case "2": getActorByName(); actorMenu(); break;
            case "3": controller.addActor(new Actor(1,"NEW", "ACTOR")); actorMenu(); break;
            case "4": changeActor(); actorMenu(); break;
            case "5": deleteActorById(); actorMenu(); break;
            default:;
        }
    }

    private static void deleteActorById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Who do you want to delete? (By Id!)");
        int id = scanner.nextInt();

        controller.deleteActorById(id);
    }

    private static void changeActor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Who do you want to change? (By Id!)");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Give the new name!");
        String name = scanner.nextLine();
        String[] nameSplit = name.split(" ");
        Actor newActor = new Actor(id, nameSplit[0], nameSplit[1]);

        controller.updateActor(id, newActor);
    }

    private static void getActorByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Who do you want to find?");
        String input = scanner.nextLine();

        controller.writeOutActorByName(input);
    }
}