package com.aos.watchlist.model;

import javax.annotation.processing.Generated;

import jakarta.persistence.*;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false, length = 100)
    private String name;

    
    private int year;
    private String director;
    private double review;

    public Movie(String name, int year, String director, double review) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.review = review;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public double getReview() {
        return review;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setReview(double review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "Movie name: " + name + "\nRelease date" + year + "\nMovie director: " + director + "Review: " + review;
    }
}
