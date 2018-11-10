package com.thadocizn.sprintchallenge.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.thadocizn.sprintchallenge.Movie;
import com.thadocizn.sprintchallenge.repo.MovieViewRepository;

import java.util.ArrayList;


public class MovieViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Movie>> movieList;
    private MovieViewRepository repo;
    public LiveData<ArrayList<Movie>> getMovieList(){
        if (movieList == null){

            loadList();
        }
        return movieList;
    }

    private void loadList(){
        repo = new MovieViewRepository();
        movieList = repo.getMovies();
    }

    public void addMovie(Movie movie){
        if (movieList != null){
            movieList.setValue(repo.addMovie(movie));
        }
    }

    public void deleteMovie(Movie movie){
        if (movieList != null){
            movieList.setValue(repo.deleteMovie(movie));
        }
    }

}
