package com.example.movieboxproject.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.movieboxproject.Data.Movie;
import com.example.movieboxproject.Data.User;
import com.example.movieboxproject.Database.DatabaseInstance;
import com.example.movieboxproject.R;

import java.util.ArrayList;
import java.util.List;

public class AddMovieActivity extends AppCompatActivity {


    private EditText etTitle;
    private EditText etDirector;
    private EditText etYear;
    private Spinner spGenre;
    private RatingBar rbRating;
    private Button saveMovieButton;
    private String username;

    DatabaseInstance database;

    List<Movie> list = new ArrayList<>();

    Intent intent = new Intent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        database = DatabaseInstance.getInstance(this);

        username = getIntent().getStringExtra("username");
        etTitle = findViewById(R.id.et_title);
        etDirector = findViewById(R.id.et_director);
        etYear = findViewById(R.id.et_year);
        spGenre = findViewById(R.id.sp_genre);
        rbRating = findViewById(R.id.rb_rating);
        saveMovieButton = findViewById(R.id.btn_save_movie);
        //activateListeners();

        saveMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createMovie();
            }
        });

        Movie movie = (Movie) getIntent().getSerializableExtra("Obj");
        if (movie != null) {
            int pos = getIntent().getIntExtra("position", 0);
            etTitle.setText(movie.getTitle());
            etDirector.setText(movie.getDirector());
            etYear.setText(movie.getYear());
            spGenre.setSelection(getIndex(spGenre, movie.getGenre()));
            rbRating.setRating(Float.parseFloat(movie.getRating()));

            intent.putExtra("position", pos);
        }


    }

    private int getIndex(Spinner spinner, String myString) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                return i;
            }
        }

        return 0;
    }


    private void createMovie() {
        if (validate()) {

            String title = etTitle.getText().toString();
            String director = etDirector.getText().toString();
            String year = etYear.getText().toString();
            String genre = String.valueOf(spGenre.getSelectedItem());
            String rating = String.valueOf(rbRating.getRating());


            Movie movie = new Movie();

            movie.setTitle(title);
            movie.setDirector(director);
            movie.setYear(year);
            movie.setGenre(genre);
            movie.setRating(rating);


            User savedUser = database.getUsersDAO().getUser(username);

            movie.userID = savedUser.id;

            database.getMoviesDAO().insertMovie(movie);



            intent.putExtra("Object", movie);
            setResult(RESULT_OK, intent);
            finish();

            Toast.makeText(AddMovieActivity.this, movie.getTitle() + " was added to your collection", Toast.LENGTH_LONG).show();


        }

    }

    private boolean validate() {

        if (etTitle.getText().toString().isEmpty()) {
            etTitle.setError("Empty field");
            return false;
        }

        if (etDirector.getText().toString().isEmpty()) {
            etDirector.setError("Empty field");
            return false;
        }

        if (etYear.getText().toString().isEmpty()) {
            etYear.setError("Empty field");
            return false;
        }

        return true;
    }


}
