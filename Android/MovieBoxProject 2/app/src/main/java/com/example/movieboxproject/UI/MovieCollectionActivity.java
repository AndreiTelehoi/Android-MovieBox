package com.example.movieboxproject.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.movieboxproject.Data.Movie;
import com.example.movieboxproject.Data.MovieAdapter;
import com.example.movieboxproject.Data.User;
import com.example.movieboxproject.Database.DatabaseInstance;
import com.example.movieboxproject.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieCollectionActivity extends AppCompatActivity {

    private ListView lvSavedMovies;
    private List<Movie> movies = new ArrayList<>();
    private DatabaseInstance database;
    private String username;
    private MovieAdapter adapter;
    private Button btnTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_collection);
        username = getIntent().getStringExtra("username");
        database = DatabaseInstance.getInstance(this);
        lvSavedMovies = findViewById(R.id.lv_saved_movies);
        User currentuser = database.getUsersDAO().getUser(username);
        movies = database.getMoviesDAO().selectMovies(currentuser.id);
        adapter = new MovieAdapter(this, R.layout.movie_list_adapter, movies);
        lvSavedMovies.setAdapter(adapter);
        btnTxt = findViewById(R.id.btn_save_txt);
        btnTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String space = "\n";
                FileOutputStream fos = null;
                try {
                    fos = openFileOutput("Movies.txt",MODE_PRIVATE);
                    for(int i = 0; i < movies.size(); i++) {
                        fos.write(movies.get(i).toString().getBytes());
                        fos.write(space.getBytes());
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                Toast.makeText(MovieCollectionActivity.this, "Saved to .txt", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
