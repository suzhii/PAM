package com.example.jagadeeshkumar.packers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {


    private Pattern pattern1;
    private Pattern pattern2;
    private Pattern pattern3;
    private Pattern pattern4;
    private Pattern pattern5;
    private Pattern pattern6;
    private Pattern pattern7;
    private Matcher matcher;


    private static final String FIRSTNAME_PATTERN = "^[a-zA-Z ]{3,35}$";
    private static final String LASTNAME_PATTERN = "^[a-zA-Z ]{3,35}$";
    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
    private static final String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    private static final String MOBILE_PATTERN = "^[789][0-9]{9}$";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText firstName = (EditText) findViewById(R.id.fname);
        final EditText lastName = (EditText) findViewById(R.id.lname);
        final EditText userName = (EditText) findViewById(R.id.uname);
        final EditText dob = (EditText) findViewById(R.id.dob);
        final EditText eMail = (EditText) findViewById(R.id.email);
        final EditText pass = (EditText) findViewById(R.id.pass);
        final EditText cPass = (EditText) findViewById(R.id.cpass);
        final EditText mobileNumber = (EditText) findViewById(R.id.mob);
        Button registerButton = (Button) findViewById(R.id.regbutton);
        Button clearButton = (Button) findViewById(R.id.clearbutton);

        pattern1 = Pattern.compile(FIRSTNAME_PATTERN);
        pattern2 = Pattern.compile(LASTNAME_PATTERN);
        pattern3 = Pattern.compile(USERNAME_PATTERN);
        pattern4 = Pattern.compile(DATE_PATTERN);
        pattern5 = Pattern.compile(EMAIL_PATTERN);
        pattern6 = Pattern.compile(PASSWORD_PATTERN);
        pattern7 = Pattern.compile(MOBILE_PATTERN);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (firstName.getText().toString().length() < 1) {
                    Toast.makeText(RegisterActivity.this, "Enter a valid First name", Toast.LENGTH_LONG).show();
                } else if (!validatefname(firstName.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "First name can contain only a-z, A-Z and it should be between 3 and 35 characters only", Toast.LENGTH_LONG).show();
                } else if (lastName.getText().toString().length() < 1) {
                    Toast.makeText(RegisterActivity.this, "Enter a valid Last name", Toast.LENGTH_LONG).show();
                } else if (!validatelname(lastName.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Last name can contain only a-z, A-Z and it should be between 3 and 35 characters only", Toast.LENGTH_LONG).show();
                } else if (userName.getText().toString().length() < 1) {
                    Toast.makeText(RegisterActivity.this, "Enter a valid User name", Toast.LENGTH_LONG).show();
                } else if (!validateuname(userName.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "User name can contain only a-z, 0-9, -, _ and it should be between 3 and 15 characters only", Toast.LENGTH_LONG).show();
                } else if (!validatedate(dob.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Enter a valid Date of Birth", Toast.LENGTH_LONG).show();
                }
                else if(!validateemail(eMail.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"Enter valid E-Mail ID",Toast.LENGTH_LONG).show();
                }
                else if(pass.getText().toString().length()<1){
                    Toast.makeText(RegisterActivity.this,"Enter a valid password",Toast.LENGTH_LONG).show();
                }

                else if(!validatepassword(pass.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"Password must contain atleast one digit from 0-9, atleast one lowercase character, atleast one uppercase character, atleast one special character from @,#,$,% and it should be between 6 and 20 characters only",Toast.LENGTH_LONG).show();
                }
                else if(!pass.getText().toString().equals(cPass.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"Passwords do not match",Toast.LENGTH_LONG).show();
                }
                else if(!validatemobile(mobileNumber.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"Enter a valid mobile number",Toast.LENGTH_LONG).show();
                }
                else {

                    final String strfname = firstName.getText().toString();
                    final String strlname = lastName.getText().toString();
                    final String struname = userName.getText().toString();
                    final String strdob = dob.getText().toString();
                    final String stremail = eMail.getText().toString();
                    final String strpass = pass.getText().toString();
                    final String strmobile = mobileNumber.getText().toString();


                    Response.Listener<String> responseListener = new Response.Listener<String>(){


                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success =jsonResponse.getBoolean("success");
                                if (success){

                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    RegisterActivity.this.startActivity(intent);
                                    Toast.makeText(RegisterActivity.this,"Sign up successful!",Toast.LENGTH_LONG).show();

                                }

                                else{

                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                    builder.setMessage("Registration failed :(")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }


                            }
                            catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    };







                    RegisterRequest registerRequest = new RegisterRequest(strfname,strlname,struname,strdob,stremail,strpass,strmobile,responseListener);
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(registerRequest);



                }
            }
        });


        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstName.getText().clear();
                lastName.getText().clear();
                userName.getText().clear();
                dob.getText().clear();
                eMail.getText().clear();
                pass.getText().clear();
                cPass.getText().clear();
                mobileNumber.getText().clear();
            }
        });


    }


    public boolean validatefname(final String fname) {
        matcher = pattern1.matcher(fname);
        return matcher.matches();
    }


    public boolean validatelname(final String lname) {
        matcher = pattern2.matcher(lname);
        return matcher.matches();
    }


    public boolean validateuname(final String username) {
        matcher = pattern3.matcher(username);
        return matcher.matches();
    }


    public boolean validatedate(final String date){

        matcher = pattern4.matcher(date);

        if(matcher.matches()){

            matcher.reset();

            if(matcher.find()){

                String day = matcher.group(1);
                String month = matcher.group(2);
                int year = Integer.parseInt(matcher.group(3));

                if (day.equals("31") &&
                        (month.equals("4") || month .equals("6") || month.equals("9") ||
                                month.equals("11") || month.equals("04") || month .equals("06") ||
                                month.equals("09"))) {
                    return false; // only 1,3,5,7,8,10,12 has 31 days
                } else if (month.equals("2") || month.equals("02")) {
                    //leap year
                    if(year % 4==0){
                        if(day.equals("30") || day.equals("31")){
                            return false;
                        }else{
                            return true;
                        }
                    }else{
                        if(day.equals("29")||day.equals("30")||day.equals("31")){
                            return false;
                        }else{
                            return true;
                        }
                    }
                }else{
                    return true;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean validateemail(final String email) {

        matcher = pattern5.matcher(email);
        return matcher.matches();

    }


    public boolean validatepassword(final String pass) {

        matcher = pattern6.matcher(pass);
        return matcher.matches();

    }



    public boolean validatemobile(final String mobile) {
        matcher = pattern7.matcher(mobile);
        return matcher.matches();

    }


    }



