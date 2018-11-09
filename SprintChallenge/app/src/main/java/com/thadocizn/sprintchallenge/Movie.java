package com.thadocizn.sprintchallenge;

import java.io.Serializable;

public class Movie implements Serializable {
    private String movieName;
    private int id;

    public Movie(int id) {
        this.id = id;
    }

    public Movie(String movieName, int id) {

        this.movieName = movieName;
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
