package org.example.controller;

import org.example.model.Actor;
import org.example.service.ActorService;

import java.util.List;

public class ActorController {
    private final ActorService service;

    public ActorController(ActorService service) {
        this.service = service;
    }

    public List<Actor> getAllActors() {
        return service.getAllActors();
    }

    public Actor getActorByName(String name) {
        return service.getActorByName(name);
    }

    public void addActor(Actor actor) {
        service.addActor(actor);
    }

    public void updateActor(int id, Actor actor) {
        service.updateActor(id, actor);
    }

    public void deleteActorById(int id) {
        service.deleteActorById(id);
    }
}
