package org.example.repository;

import org.example.model.Actor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorDaoJdbc implements ActorDao{
    static final String DB_URL = "jdbc:postgresql://localhost:5432/dvdrental";

    static final String USER = "postgres";

    static final String PASS = "tehu";

    @Override
    public List<Actor> getAllActors() {
        final String SQL = "SELECT actor_id, first_name, last_name FROM actor;";

        try(Connection con = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement st = con.prepareStatement(SQL);

            ResultSet rs = st.executeQuery();
            List<Actor> actors = new ArrayList<>();

            while (rs.next()){
                Actor actor = new Actor(rs.getInt(1), rs.getString(2), rs.getString(3));
                actors.add(actor);
            }

            return actors;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Actor getActorByName(String firstName, String lastName) {
        final String SQL = "SELECT actor_id, first_name, last_name FROM actor WHERE first_name = ? AND last_name = ?;";

        try(Connection con = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, firstName);
            st.setString(2, lastName);

            ResultSet rs = st.executeQuery();

            if(!rs.next()){
                return null;
            }

            return new Actor(rs.getInt(1), rs.getString(2), rs.getString(3));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addActor(Actor actor) { // FÖLÖSLEGES ADATOK IS MENNEK. HOGYAN LEHETNE MEGOLDANI? VOLT MÁR RÓLA SZÓ -> DTO
        final String SQL = "INSERT INTO actor(first_name, last_name) VALUES (?, ?);";
        try(Connection con = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, actor.getFirstName());
            st.setString(2, actor.getLastName());
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateActor(int id, Actor actor) {
        final String SQL = "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?;";
        try(Connection con = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, actor.getFirstName());
            st.setString(2, actor.getLastName());
            st.setInt(3, id);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteActorById(int id) {
        final String SQL = "DELETE FROM actor WHERE actor_id = ?;";

        try(Connection con = DriverManager.getConnection(DB_URL, USER, PASS)){
            PreparedStatement st = con.prepareStatement(SQL);
            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
