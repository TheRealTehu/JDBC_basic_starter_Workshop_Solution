package org.example.service;

import org.example.model.Actor;
import org.example.repository.ActorDao;

import java.util.List;

public class ActorService {
    private final ActorDao repository;

    public ActorService(ActorDao repository) {
        this.repository = repository;
    }

    public List<Actor> getAllActors() {
        return repository.getAllActors();
    }

    public Actor getActorByName(String name) {
        String[] nameSplit = name.split(" ");
        String firstName = nameSplit[0];
        String lastName = nameSplit[1];
        return repository.getActorByName(firstName, lastName);
    }

    public void addActor(Actor actor) {
        repository.addActor(actor);
    }

    public void updateActor(int id, Actor actor) {
        repository.updateActor(id, actor);
    }

    public void deleteActorById(int id) {
        repository.deleteActorById(id);
    }
}
