package com.example.movieboxproject.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieboxproject.Data.Movie;
import com.example.movieboxproject.Data.User;
import com.example.movieboxproject.Database.DatabaseInstance;
import com.example.movieboxproject.MainActivity;
import com.example.movieboxproject.R;

import java.util.ArrayList;
import java.util.List;

public class UserProfileActivity extends AppCompatActivity {

    private String username;
    private TextView tv_welcome;
    private Button btnSignOut;
    private Button btnAddMovie;
    private Button btnViewCollection;
    private Button btnDelete;
    private Button btnChangePass;
    private EditText newPassword;
    private Button btnConfirm;
    private Button btnSavedMovies;
    private Button btnOldies;
    public static final Integer ADD_MOVIE_REQUEST_CODE = 100;

    private DatabaseInstance database;

    List<Movie> bigList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        database = DatabaseInstance.getInstance(this);
        initViews();
        activateListeners();


    }

    public void activateListeners() {
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goHome();
            }
        });

        btnAddMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAddMovie();
            }
        });

        btnViewCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCollection();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User currentUser = database.getUsersDAO().getUser(username);
                if(currentUser!=null){
                    database.getUsersDAO().deleteUser(currentUser);
                    Toast.makeText(UserProfileActivity.this, "User deleted", Toast.LENGTH_SHORT).show();
                }

                goHome();

            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User currentUser = database.getUsersDAO().getUser(username);
                if(currentUser!=null){
                    if(newPassword.getText().toString().length() > 5){
                        currentUser.password = newPassword.getText().toString();
                        database.getUsersDAO().updateUser(currentUser);
                    }
                    else {
                        newPassword.setError("password should be at least 6 characters");
                    }
                }
                Toast.makeText(UserProfileActivity.this, "Password changed. Please sign in again.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UserProfileActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPassword.setVisibility(View.VISIBLE);
                btnConfirm.setVisibility(View.VISIBLE);
            }
        });

        btnSavedMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfileActivity.this, MovieCollectionActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        btnOldies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfileActivity.this, OldMoviesActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }




    public void startCollection() {
        Intent intent = new Intent(UserProfileActivity.this, TemporaryCollectionActivity.class);
        intent.putParcelableArrayListExtra("List", (ArrayList) bigList);
        startActivity(intent);
    }

    public void startAddMovie() {
        Intent intent = new Intent(UserProfileActivity.this, AddMovieActivity.class);
        intent.putExtra("username", username);
        startActivityForResult(intent, ADD_MOVIE_REQUEST_CODE);
    }

    public void goHome() {
        Intent intent = new Intent(UserProfileActivity.this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("You must sign out");
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //nothing
            }
        });
        builder.show();
    }

    private void initViews() {


        username = getIntent().getStringExtra("username");
        tv_welcome = findViewById(R.id.tv_welcome);
        tv_welcome.setText(tv_welcome.getText() + " " + username);
        btnSignOut = findViewById(R.id.btn_sign_out);
        btnAddMovie = findViewById(R.id.btn_add_movie);
        btnViewCollection = findViewById(R.id.btn_view_collection);
        btnDelete = findViewById((R.id.btn_delete_profile));
        btnChangePass = findViewById(R.id.btn_change_password);
        newPassword = findViewById(R.id.et_change_pass);
        btnConfirm = findViewById(R.id.btn_confirm);
        btnSavedMovies = findViewById(R.id.btn_view_saved_collection);
        btnOldies = findViewById(R.id.btn_view_oldies_collection);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == ADD_MOVIE_REQUEST_CODE) {
            if (data == null) return;
            Movie movie = (Movie) data.getSerializableExtra("Object");
            bigList.add(movie);
        }


    }


}
