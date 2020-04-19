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

public class OldMoviesActivity extends AppCompatActivity {

    private String getUsername;
    private ListView lvSavedMovies;
    private List<Movie> movies = new ArrayList<>();
    private DatabaseInstance database;
    private String username;
    private MovieAdapter adapter;
    private Button btnTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_movies);
        username = getIntent().getStringExtra("username");
        database = DatabaseInstance.getInstance(this);
        lvSavedMovies = findViewById(R.id.lv_old_movies);
        User currentUser = database.getUsersDAO().getUser(username);
        movies = database.getMoviesDAO().selectOldMovies(currentUser.id);
        adapter = new MovieAdapter(this, R.layout.movie_list_adapter, movies);
        lvSavedMovies.setAdapter(adapter);
        btnTxt = findViewById(R.id.btn_save_txt2);
        btnTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String space = "\n";
                FileOutputStream fos = null;
                try {
                    fos = openFileOutput("OldMovies.txt",MODE_PRIVATE);
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
                Toast.makeText(OldMoviesActivity.this, "Saved to .txt", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
