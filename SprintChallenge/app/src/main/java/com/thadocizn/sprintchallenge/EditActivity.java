package com.thadocizn.sprintchallenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    public static final String EDIT_MOVIE_KEY = "edit_movie";
    EditText editMovie;
    Movie movie;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editMovie = findViewById(R.id.editMovie);
        context = this;

        findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        movie = (Movie)getIntent().getSerializableExtra(EDIT_MOVIE_KEY);
        if (movie == null){
            movie = new Movie(Movie.NO_ID);
        }
    }
    private void prepResult() {
        movie.setMovieName(editMovie.getText().toString());
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EDIT_MOVIE_KEY, movie);
        setResult(Activity.RESULT_OK, resultIntent);

    }

    @Override
    protected void onPause() {
        super.onPause();
        prepResult();
    }

    @Override
    public void onBackPressed() {
        prepResult();
        super.onBackPressed();

    }
}
