package com.thadocizn.sprintchallenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.thadocizn.sprintchallenge.viewModel.MovieViewModel;

public class EditActivity extends AppCompatActivity {
    public static final String TAG = EditActivity.class.getSimpleName();
    public static final String EDIT_MOVIE_KEY = "edit_movie";
    public static final String WATCHED_MOVIE = "watched_movie";

    private MovieViewModel viewModel;


    EditText editMovie;
    Movie movie;
    Context context;
    private static Boolean chkBox ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        movie = (Movie)getIntent().getSerializableExtra(EDIT_MOVIE_KEY);

        editMovie = findViewById(R.id.editMovie);
        context = this;
        final CheckBox checkBox = findViewById(R.id.checkBoxWatchedMovie);

        findViewById(R.id.buttonDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(EDIT_MOVIE_KEY, movie);
                setResult(ListActivity.DELETE_RESULT_CODE, resultIntent);
                finish();
            }
        });

        findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean watched;
                if (checkBox.isChecked()){
                    watched = true;

                }else {
                    watched = false;

                }
                chkBox = watched;
                prepResult();
            }
        });


        if (movie == null){
            movie = new Movie(Movie.NO_ID);
            movie.setMovieName("");
        }
        editMovie.setText(movie.getMovieName());
    }

    public static Boolean getData(){
        return chkBox;
    }
    private void prepResult() {
        movie.setMovieName(editMovie.getText().toString());
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EDIT_MOVIE_KEY, movie);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();

    }

    @Override
    protected void onPause() {
        super.onPause();
        prepResult();
    }

   /* @Override
    public void onBackPressed() {
        prepResult();
        super.onBackPressed();

    }*/
}
