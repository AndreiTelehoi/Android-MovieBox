package com.example.movieboxproject.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.movieboxproject.Database.DatabaseInstance;
import com.example.movieboxproject.R;

public class SignInActivity extends AppCompatActivity {

    private Button btnSignIN;
    private EditText etUsername;
    private EditText etPassword;
    private DatabaseInstance database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        database = DatabaseInstance.getInstance(this);
        initViews();
        activateListeners();
    }

    public void initViews() {
        btnSignIN = findViewById(R.id.btn_sign_in_done);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
    }

    public void activateListeners() {
        btnSignIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    openUserProfileActivity();
                }
            }
        });
    }

    public boolean validate() {


        if (database.getUsersDAO().userExists(etUsername.getText().toString(),etPassword.getText().toString())==0){
            etPassword.setError("invalid password or user does not exist");
            return false;
        }

        if (etUsername.getText().toString().isEmpty()) {
            etUsername.setError("enter the username");
            return false;
        }

        if (etPassword.getText().toString().isEmpty()) {
            etPassword.setError("enter the password");
            return false;
        }
        return true;
    }

    public void openUserProfileActivity() {
        Intent intent = new Intent(this, UserProfileActivity.class);
        intent.putExtra("username", etUsername.getText().toString());
        startActivity(intent);
    }
}
