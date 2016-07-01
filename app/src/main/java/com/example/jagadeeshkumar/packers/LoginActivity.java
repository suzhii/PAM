package com.example.jagadeeshkumar.packers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jagadeesh Kumar on 15-06-2016.
 */

public class LoginActivity extends AppCompatActivity {

     @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         final EditText logUname = (EditText) findViewById(R.id.loguname);
         final EditText logPass = (EditText) findViewById(R.id.logpass);
         Button logButton = (Button) findViewById(R.id.logbutton);
         TextView logRegister = (TextView) findViewById(R.id.reg);

         logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strlogUname = logUname.getText().toString();
                String strlogPass = logPass.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try
                        {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success)
                            {
                                String firstname = jsonResponse.getString("firstname");
                                String lastname = jsonResponse.getString("lastname");
                                String dateofbirth = jsonResponse.getString("dateofbirth");
                                String email = jsonResponse.getString("email");
                                String mobilenumber = jsonResponse.getString("mobilenumber");


                                Intent intent = new Intent(LoginActivity.this, BookingActivity.class);
                                intent.putExtra("firstname",firstname);
                                intent.putExtra("lastname",lastname);
                                intent.putExtra("dateofbirth",dateofbirth);
                                intent.putExtra("email",email);
                                intent.putExtra("mobilenumber",mobilenumber);
                                LoginActivity.this.startActivity(intent);
                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }
                };


                LoginRequest loginRequest = new LoginRequest(strlogUname,strlogPass, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);


                //Toast.makeText(LoginActivity.this, "Successfully logged in!", Toast.LENGTH_LONG).show();
            }
        });

        logRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        });
    }
}

