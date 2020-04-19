package com.example.movieboxproject.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.movieboxproject.Data.Movie;
import com.example.movieboxproject.Data.MovieAdapter;
import com.example.movieboxproject.R;

import java.util.ArrayList;
import java.util.List;

public class TemporaryCollectionActivity extends AppCompatActivity {


    ListView listView;
    MovieAdapter adapter;
    List<Movie> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporary_collection);

        listView = findViewById(R.id.lv_movies);

        list = (List) getIntent().getParcelableArrayListExtra("List");

        adapter = new MovieAdapter(this, R.layout.movie_list_adapter, list);

        listView.setAdapter(adapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(TemporaryCollectionActivity.this, AddMovieActivity.class);
//                intent.putExtra("Obj", (Movie) listView.getItemAtPosition(position));
//                intent.putExtra("position", position);
//                startActivityForResult(intent, 2);
//            }
//        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK) {
            int pos = data.getIntExtra("position", 0);
            Movie movie = (Movie) data.getSerializableExtra("Object");
            if (pos >= 0 && pos < list.size() && movie != null) {
                list.set(pos, movie);
                adapter.notifyDataSetChanged();
            }
        }
    }


}
