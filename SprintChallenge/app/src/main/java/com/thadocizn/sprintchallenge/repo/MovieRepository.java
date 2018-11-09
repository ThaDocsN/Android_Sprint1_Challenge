package com.thadocizn.sprintchallenge.repo;

import android.arch.lifecycle.MutableLiveData;

import com.thadocizn.sprintchallenge.Movie;

import java.util.ArrayList;

public class MovieRepository {
    private ArrayList<Movie> movies;

    public MovieRepository() {
        this.movies = new ArrayList<>();
    }
    public MutableLiveData<ArrayList<Movie>> getNotes(){
        MutableLiveData<ArrayList<Movie>> liveDataList = new MutableLiveData<>();
        liveDataList.setValue(movies);
        return liveDataList;
    }
    public ArrayList<Movie> addMovie(Movie movie){

        if (movie.getId() == Movie.NO_ID){
            int index = movies.size();
            movie.setId(index);
            movies.add(movie);

        }else {
            movies.set(movie.getId(), movie);
        }
        return movies;
    }
}
