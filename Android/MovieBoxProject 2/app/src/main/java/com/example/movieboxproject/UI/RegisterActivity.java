package com.example.movieboxproject.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movieboxproject.Data.User;
import com.example.movieboxproject.Database.DatabaseInstance;
import com.example.movieboxproject.MainActivity;
import com.example.movieboxproject.R;

public class RegisterActivity extends AppCompatActivity {

    private Button btnSubmit;
    private EditText etFName;
    private EditText etLName;
    private EditText etEmail;
    private EditText etUsername;
    private EditText etPassword;
    private CheckBox checkBox;


    private DatabaseInstance database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = DatabaseInstance.getInstance(this);
        setContentView(R.layout.activity_register);
        initViews();
        activateListeners();
    }

    public void initViews() {
        btnSubmit = findViewById(R.id.btn_submit_registration);
        etFName = findViewById(R.id.et_first_name);
        etLName = findViewById(R.id.et_last_name);
        etEmail = findViewById(R.id.et_email);
        etUsername = findViewById(R.id.et_r_username);
        etPassword = findViewById(R.id.et_r_password);
        checkBox = findViewById(R.id.cb_terms);

    }

    public void activateListeners() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()) {

                    createNewUser();
                    Toast.makeText(RegisterActivity.this, "User created", Toast.LENGTH_SHORT).show();
                    startMainActivity();

                }
            }
        });
    }

    public void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public boolean validate(){
        if(etUsername.getText().toString().length() < 3){
            etUsername.setError("enter at least 3 characters");
            return false;
        }

        if(etFName.getText().toString().isEmpty()){
            etFName.setError("empty field");
            return false;
        }

        if(etLName.getText().toString().isEmpty()){
            etLName.setError("empty field");
            return false;
        }

        if(etEmail.getText().toString().isEmpty()){
            etEmail.setError("empty field");
            return false;
        }

        if(etPassword.getText().toString().length() < 6){
            etPassword.setError("password must have at least 6 characters");
            return false;
        }

        if(database.getUsersDAO().checkUsername(etUsername.getText().toString()) != 0){
            etUsername.setError("username exists already");
            return false;
        }

        if(!checkBox.isChecked()){
            checkBox.setError("you must agree to the terms and conditions");
            return false;
        }

        return true;
    }

    public void createNewUser(){

        User newUser = new User();
        newUser.username = etUsername.getText().toString();
        newUser.password = etPassword.getText().toString();
        newUser.firstName = etFName.getText().toString();
        newUser.lastName = etLName.getText().toString();
        newUser.email = etEmail.getText().toString();
        database.getUsersDAO().insertUser(newUser);

    }

}
