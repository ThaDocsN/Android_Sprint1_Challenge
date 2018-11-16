package com.thadocizn.sprintchallenge.repo;

import android.arch.lifecycle.MutableLiveData;

import com.thadocizn.sprintchallenge.Movie;

import java.util.ArrayList;

public class MovieViewRepository {
    private ArrayList<Movie> movies;

    public MovieViewRepository() {
        this.movies = new ArrayList<>();
    }
    public MutableLiveData<ArrayList<Movie>> getMovies(){
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
    public ArrayList<Movie> deleteMovie(Movie movie){

        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getId() == movie.getId()) {
                movies.remove(i);
            }
        }
        return movies;
    }

}
