package org.example.model;

import java.sql.Date;
import java.util.List;

public class Film {
    private int id;
    private String title;
    private String description;
    private int releaseYear;
    private int languageId;
    private int rentalDuration;
    private double rentalRate;
    private int length;
    private double replacementCost;
    private Rating rating;
    private Date lastUpdate;
    private List<String> specialFeatures;

    public Film() {
    }

    public Film(int id, String title, String description, int releaseYear, int languageId, int rentalDuration, double rentalRate, int length, double replacementCost, Rating rating, Date lastUpdate, List<String> specialFeatures) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.languageId = languageId;
        this.rentalDuration = rentalDuration;
        this.rentalRate = rentalRate;
        this.length = length;
        this.replacementCost = replacementCost;
        this.rating = rating;
        this.lastUpdate = lastUpdate;
        this.specialFeatures = specialFeatures;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public int getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(int rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(double rentalRate) {
        this.rentalRate = rentalRate;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(double replacementCost) {
        this.replacementCost = replacementCost;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<String> getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(List<String> specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    @Override
    public String toString() {
        return "Film: " +
                "id= " + id +
                ", title= " + title + '\'' +
                ", description= " + description + '\'' +
                ", releaseYear= " + releaseYear +
                ", languageId= " + languageId +
                ", rentalDuration= " + rentalDuration +
                ", rentalRate= " + rentalRate +
                ", length= " + length +
                ", replacementCost= " + replacementCost +
                ", rating= " + rating +
                ", lastUpdate= " + lastUpdate +
                ", specialFeatures= " + specialFeatures;
    }
}
