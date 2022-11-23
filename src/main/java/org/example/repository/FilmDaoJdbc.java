package org.example.repository;

import org.example.model.Film;
import org.example.model.Rating;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilmDaoJdbc implements FilmDao {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/dvdrental";

    static final String USER = "postgres";

    static final String PASS = "postgres";

    @Override
    public List<Film> getAllFilms() {
        final String SQL = "SELECT film_id, title, description, release_year, language_id, rental_duration, rental_rate, " +
                "length, replacement_cost, rating, last_update, special_features, " +
                "(unnest(fulltext)).lexeme FROM film group by film_id order by film_id asc;";

        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement st = con.prepareStatement(SQL);

            ResultSet rs = st.executeQuery();
            List<Film> films = new ArrayList<>();

            Film film = new Film();

            while (rs.next()) {
                if(film.getId() == null){
                    film.setId(rs.getInt(1));
                    film.setTitle(rs.getString(2));
                    film.setDescription(rs.getString(3));
                    film.setReleaseYear(rs.getInt(4));
                    film.setLanguageId(rs.getInt(5));
                    film.setRentalDuration(rs.getInt(6));
                    film.setRentalRate(rs.getDouble(7));
                    film.setLength(rs.getInt(8));
                    film.setReplacementCost(rs.getDouble(9));
                    film.setRating(getProperRating(rs.getString(10)));
                    film.setLastUpdate(rs.getDate(11));
                    film.setSpecialFeatures(getSpecialFeatures(rs.getString(12)));
                    film.addToFulLText(rs.getString(13));
                } else if(film.getId() == rs.getInt(1)){
                    film.addToFulLText(rs.getString(13));
                } else {
                    films.add(film);
                    film = new Film();

                    film.setId(rs.getInt(1));
                    film.setTitle(rs.getString(2));
                    film.setDescription(rs.getString(3));
                    film.setReleaseYear(rs.getInt(4));
                    film.setLanguageId(rs.getInt(5));
                    film.setRentalDuration(rs.getInt(6));
                    film.setRentalRate(rs.getDouble(7));
                    film.setLength(rs.getInt(8));
                    film.setReplacementCost(rs.getDouble(9));
                    film.setRating(getProperRating(rs.getString(10)));
                    film.setLastUpdate(rs.getDate(11));
                    film.setSpecialFeatures(getSpecialFeatures(rs.getString(12)));
                    film.addToFulLText(rs.getString(13));
                }

            }

            films.add(film);

            return films;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<String> getSpecialFeatures(String features) {
        return Arrays.asList(features.split(","));
    }

    private Rating getProperRating(String rating) {
        return switch (rating) {
            case "G" -> Rating.G;
            case "PG" -> Rating.PG;
            case "PG-13" -> Rating.PG13;
            case "NC-17" -> Rating.NC17;
            case "R" -> Rating.R;
            default -> Rating.R;
        };
    }

    @Override
    public void addFilm(Film film) {
        final String SQL = "INSERT INTO film(title, description, release_year, language_id, rental_duration, " +
                "rental_rate, length, replacement_cost, rating, last_update, special_features) VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?::mpaa_rating, ?, ?)";

        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, film.getTitle());
            st.setString(2, film.getDescription());
            st.setInt(3, film.getReleaseYear());
            st.setInt(4, film.getLanguageId());
            st.setInt(5, film.getRentalDuration());
            st.setDouble(6, film.getRentalRate());
            st.setInt(7, film.getLength());
            st.setDouble(8, film.getReplacementCost());
            st.setObject(9, ratingToString(film.getRating()));
            st.setDate(10, film.getLastUpdate());
            st.setObject(11, featuresToStringArray(film.getSpecialFeatures()));

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String[] featuresToStringArray(List<String> specialFeatures) {
        String[] array = new String[specialFeatures.size()];
        for (int i = 0; i < specialFeatures.size(); i++) {
            array[i] = specialFeatures.get(i);
        }

        return array;
    }

    private String ratingToString(Rating rating) {
        return switch (rating) {
            case G -> "G";
            case PG -> "PG";
            case PG13 -> "PG-13";
            case NC17 -> "NC-17";
            case R -> "R";
        };
    }

    @Override
    public Film findFilmByTitle(String title) {
        final String SQL = "SELECT film_id, title, description, release_year, language_id, rental_duration, rental_rate," +
                " length, replacement_cost, rating, last_update, special_features FROM film WHERE title = ?;";

        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, title);

            ResultSet rs = st.executeQuery();

            if (!rs.next()) {
                return null;
            }

            return new Film(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
                    rs.getInt(5), rs.getInt(6), rs.getDouble(7), rs.getInt(8),
                    rs.getDouble(9), getProperRating(rs.getString(10)), rs.getDate(11),
                    getSpecialFeatures(rs.getString(12)));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateFilm(int id, Film film) {
        final String SQL = "UPDATE film SET title = ?, description = ?, release_year = ?, language_id = ?, " +
                "rental_duration = ?, rental_rate = ?, length = ?, replacement_cost = ?, rating = ?::mpaa_rating, last_update = ?, " +
                "special_features = ? WHERE film_id = ?;";

        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement st = con.prepareStatement(SQL);

            st.setString(1, film.getTitle());
            st.setString(2, film.getDescription());
            st.setInt(3, film.getReleaseYear());
            st.setInt(4, film.getLanguageId());
            st.setInt(5, film.getRentalDuration());
            st.setDouble(6, film.getRentalRate());
            st.setInt(7, film.getLength());
            st.setDouble(8, film.getReplacementCost());
            st.setString(9, ratingToString(film.getRating()));
            st.setDate(10, film.getLastUpdate());
            st.setObject(11, featuresToStringArray(film.getSpecialFeatures()));

            st.setInt(12, id);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFilmById(int id) {
        final String SQL = "DELETE FROM film WHERE film_id = ?;";

        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement st = con.prepareStatement(SQL);
            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
