package com.example.movieboxproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.movieboxproject.Data.JsonRead;
import com.example.movieboxproject.Data.Movie;
import com.example.movieboxproject.Database.PrefsManager;
import com.example.movieboxproject.UI.RegisterActivity;
import com.example.movieboxproject.UI.SignInActivity;
import com.example.movieboxproject.UI.TrendingMoviesActivity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button btnSignIn;
    private Button btnRegister;
    private Button btnTrending;
    private Button btnPrefs;
    private PrefsManager prefsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        prefsManager = PrefsManager.getInstance(getApplicationContext());
        savePrefs();
        activateListeners();

    }

    private void savePrefs() {
        prefsManager.writeInt("version", 3);
        prefsManager.writeString("app_name", "moviebox");
    }

    private void initViews() {
        btnSignIn = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        btnTrending = findViewById(R.id.btn_trending);
        btnPrefs = findViewById(R.id.btn_prefsr);
    }

    private void activateListeners() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignInActivty();
            }
        });
        btnTrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTrendingActivity();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegistrationForm();
            }
        });
        btnPrefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int intValue = prefsManager.readInt("version");
                String stringValue = prefsManager.readString("app_name");
                Toast.makeText(MainActivity.this, "version = "+intValue+"; app_name = "+stringValue, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openTrendingActivity() {
        Intent intent = new Intent(this, TrendingMoviesActivity.class);
        startActivity(intent);
        Toast.makeText(MainActivity.this, "Loading movies from JSON...", Toast.LENGTH_SHORT).show();
    }

    private void openSignInActivty() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    private void openRegistrationForm() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy");
    }
}
