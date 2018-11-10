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

public class EditActivity extends AppCompatActivity {

    public static final String EDIT_MOVIE_KEY = "edit_movie";
    public static final String WATCHED_MOVIE = "watched_movie";
    EditText editMovie;
    Movie movie;
    Context context;
    private static Boolean chkBox ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editMovie = findViewById(R.id.editMovie);
        context = this;
        final CheckBox checkBox = findViewById(R.id.checkBoxWatchedMovie);

        findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    chkBox = true;
                    Log.i("Charles", Boolean.toString(chkBox));

                }else {
                    chkBox = false;
                    Log.i("Charles", Boolean.toString(chkBox));

                }
                onBackPressed();
            }
        });

        movie = (Movie)getIntent().getSerializableExtra(EDIT_MOVIE_KEY);
        if (movie == null){
            movie = new Movie(Movie.NO_ID);
        }
    }

    public static Boolean getData(){
        return chkBox;
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
