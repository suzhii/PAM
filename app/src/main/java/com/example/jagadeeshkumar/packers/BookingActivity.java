package com.example.jagadeeshkumar.packers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jagadeesh Kumar on 30-06-2016.
 */
public class BookingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        final EditText source = (EditText) findViewById(R.id.etsource);
        final EditText destination = (EditText) findViewById(R.id.etdestination);
        final EditText pickupdate = (EditText) findViewById(R.id.etdate);
        CheckBox industrial = (CheckBox) findViewById(R.id.cbindustrial);
        CheckBox domestic = (CheckBox) findViewById(R.id.cbdomestic);
        CheckBox item3 = (CheckBox) findViewById(R.id.cbitem3);
        CheckBox others = (CheckBox) findViewById(R.id.cbothers);
        Button submitbutton = (Button) findViewById(R.id.submitbutton);


        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strsource = source.getText().toString();
                String strdestination = destination.getText().toString();
                String strpickupdate = pickupdate.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>(){


                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success =jsonResponse.getBoolean("success");
                            if (success){

                               // Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                               // RegisterActivity.this.startActivity(intent);
                                Toast.makeText(BookingActivity.this, "Booking successful!", Toast.LENGTH_LONG).show();

                            }

                            else{

                                AlertDialog.Builder builder = new AlertDialog.Builder(BookingActivity.this);
                                builder.setMessage("Booking failed :(")
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







                BookingRequest bookingRequest = new BookingRequest(strsource, strdestination, strpickupdate,responseListener);
                RequestQueue queue = Volley.newRequestQueue(BookingActivity.this);
                queue.add(bookingRequest);




            }
        });
    }
}
