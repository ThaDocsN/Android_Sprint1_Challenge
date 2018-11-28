package com.thadocizn.sprintchallenge;

import java.io.Serializable;

public class Movie implements Serializable {

    public static final int NO_ID = -1;

    private boolean watched;
    private String movieName;
    private int id;

    public Movie(int id) {
        this.id = id;
    }

    public Movie(String movieName, int id, boolean watched) {

        this.movieName = movieName;
        this.id = id;
        this.watched = watched;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
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
