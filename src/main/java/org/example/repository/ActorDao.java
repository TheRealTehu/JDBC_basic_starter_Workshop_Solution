package org.example.repository;

import org.example.model.Actor;

import java.util.List;

public interface ActorDao {
    List<Actor> getAllActors();
    Actor getActorByName(String firstName, String lastName);
    void addActor(Actor actor);
    void updateActor(int id, Actor actor);
    void deleteActorById(int id);
}
