package com.example.movieboxproject.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.movieboxproject.Data.JsonRead;
import com.example.movieboxproject.Data.Movie;
import com.example.movieboxproject.Data.MovieAdapter;
import com.example.movieboxproject.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TrendingMoviesActivity extends AppCompatActivity {

    private ListView lvMovies;
    private MovieAdapter adapter;
    private List<Movie> movies = new ArrayList<>();

    public TrendingMoviesActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_movies);
        connect();
        lvMovies = findViewById(R.id.lv_tmovies);

        adapter = new MovieAdapter(TrendingMoviesActivity.this, R.layout.movie_list_adapter, movies);
        lvMovies.setAdapter(adapter);

    }

    private void connect() {
        JsonRead jsonRead = new JsonRead() {
            @Override
            protected void onPostExecute(String s) {
                List<Movie> movies = parseJson(s);
                adapter.updateList(movies);
            }
        };
        try {
            jsonRead.execute(new URL("https://api.myjson.com/bins/193wwm"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
