package com.thadocizn.sprintchallenge;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thadocizn.sprintchallenge.viewModel.MovieViewModel;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private Context context;
    private LinearLayout listLayout;
    private MovieViewModel viewModel;
    Movie movie;
/*
    ArrayList<Movie> movies;
*/

    public static final int EDIT_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        context = this;
        listLayout = findViewById(R.id.listItem);
        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
/*
        movies = new ArrayList<Movie>();
*/

        final Observer<ArrayList<Movie>> observer = new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Movie> movies) {
                if (movies != null){
                    refreshListView(movies);
                }
            }
        };
        viewModel.getMovieList().observe(this, observer);

        findViewById(R.id.buttonAddMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(context, EditActivity.class);
                    Movie newMovie = new Movie(Movie.NO_ID);
                    intent.putExtra(EditActivity.EDIT_MOVIE_KEY, newMovie);
                    startActivityForResult(intent, EDIT_REQUEST_CODE);


                }
        });

    }

    private TextView getDefaultTextView(final Movie movie) {
        TextView textView = new TextView(context);
        textView.setText(movie.getMovieName());
        textView.setTextSize(24);
        textView.setPadding(10, 10, 10, 10);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra(EditActivity.EDIT_MOVIE_KEY, movie);
                startActivityForResult(intent, EDIT_REQUEST_CODE);
            }
        });

        return textView;
    }
    private void refreshListView(ArrayList<Movie> movies) {
        listLayout.removeAllViews();
        for(Movie movie: movies) {
            listLayout.addView(getDefaultTextView(movie));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK) {
            if(requestCode == EDIT_REQUEST_CODE) {
                if(data != null) {
                    Movie returnedMovie = (Movie) data.getSerializableExtra(EditActivity.EDIT_MOVIE_KEY);

                    viewModel.addMovie(returnedMovie);
                }
            }
        }
    }
}
