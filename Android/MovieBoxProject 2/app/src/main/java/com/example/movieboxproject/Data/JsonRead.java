package com.example.movieboxproject.Data;

import android.os.AsyncTask;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JsonRead extends AsyncTask<URL, Void, String> {
    @Override
    protected String doInBackground(URL... urls) {

        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) urls[0].openConnection();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            inputStreamReader.close();
            inputStream.close();
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    protected List<Movie> parseJson(String json) {
        List<Movie> movies = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray moviesObject = jsonObject.getJSONArray("movies");
            for (int i = 0; i < moviesObject.length(); i++) {
                JSONObject currentObject = moviesObject.getJSONObject(i);
                Movie movie = new Movie();
                movie.setTitle(currentObject.getString("title"));
                movie.setDirector(currentObject.getString("director"));
                movie.setYear(currentObject.getString("year"));

                movies.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return movies;
    }
}
