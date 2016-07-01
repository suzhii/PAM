package com.example.jagadeeshkumar.packers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Jagadeesh Kumar on 19-06-2016.
 */
public class userAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final EditText etFname = (EditText)findViewById(R.id.etFname);
        final EditText etLname = (EditText)findViewById(R.id.etLname);
        final EditText etEmail = (EditText)findViewById(R.id.etEmail);
        final EditText etmobilenumber = (EditText)findViewById(R.id.etmobilenumber);
        final EditText etdateofbirth = (EditText)findViewById(R.id.etdob);

        final TextView welcomeMessage = (TextView)findViewById(R.id.tvWelcomeMsg);
        Intent intent = getIntent();
        String fname = intent.getStringExtra("firstname");
        String lname = intent.getStringExtra("lastname");
        String dob = intent.getStringExtra("dateofbirth");
        String email = intent.getStringExtra("email");
        String mobilenumber = intent.getStringExtra("mobilenumber");
        //int age = intent.getIntExtra("age", -1);

        String message = fname + "Welcome to your user area";
        welcomeMessage.setText(message);
        etFname.setText(fname);
        etLname.setText(lname);
        etEmail.setText(email);
        etdateofbirth.setText(dob);
        etmobilenumber.setText(mobilenumber);

    }
}
