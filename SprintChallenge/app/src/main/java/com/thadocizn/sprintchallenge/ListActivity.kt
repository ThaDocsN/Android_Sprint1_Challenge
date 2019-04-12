package com.thadocizn.sprintchallenge

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

import com.thadocizn.sprintchallenge.viewModel.MovieViewModel

import java.util.ArrayList

class ListActivity : AppCompatActivity() {

    private var context: Context? = null
    private var listLayout: LinearLayout? = null
    private var viewModel: MovieViewModel? = null
    internal var movie: Movie? = null
    internal var bool: Boolean? = false
    internal var textView: TextView? = null


    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        context = this
        listLayout = findViewById(R.id.listItem)
        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        val observer = Observer<ArrayList<Movie>> { movies ->
            if (movies != null) {
                refreshListView(movies)
            }
        }
        viewModel!!.movieList.observe(this, observer)

        findViewById(R.id.buttonAddMovie).setOnClickListener(View.OnClickListener {
            val intent = Intent(context, EditActivity::class.java)
            val newMovie = Movie(Movie.NO_ID)
            intent.putExtra(EditActivity.EDIT_MOVIE_KEY, newMovie)
            startActivityForResult(intent, EDIT_REQUEST_CODE)
        })

    }

    private fun refreshListView(movies: ArrayList<Movie>) {
        listLayout!!.removeAllViews()
        for (movie in movies) {
            listLayout!!.addView(getDefaultTextView(movie))
        }
    }

    private fun getDefaultTextView(movie: Movie): TextView {


        textView = TextView(context)
        textView.text = movie.movieName
        if (movie.isWatched) {
            textView!!.setTextColor(Color.BLUE)
            textView!!!!.paintFlags = textView!!.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
        textView!!.textSize = 24f
        textView!!.setPadding(10, 10, 10, 10)
        textView!!.setOnClickListener {
            val intent = Intent(context, EditActivity::class.java)
            intent.putExtra(EditActivity.EDIT_MOVIE_KEY, movie)
            startActivityForResult(intent, EDIT_REQUEST_CODE)
        }

        return textView!!
    }

    protected override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == EDIT_REQUEST_CODE) {
                if (data != null) {
                    val returnedMovie = data.getSerializableExtra(EditActivity.EDIT_MOVIE_KEY) as Movie

                    viewModel!!.addMovie(returnedMovie)
                }
            }
        } else {
            if (data != null) {
                val returnMovie = data.getSerializableExtra(EditActivity.EDIT_MOVIE_KEY) as Movie
                viewModel!!.deleteMovie(returnMovie)
            }
        }
    }

    companion object {

        val EDIT_REQUEST_CODE = 1
    }
}
