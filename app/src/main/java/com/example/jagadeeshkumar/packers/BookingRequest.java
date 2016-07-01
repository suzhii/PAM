package com.example.jagadeeshkumar.packers;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jagadeesh Kumar on 01-07-2016.
 */
public class BookingRequest extends StringRequest {

    private static final String BOOKING_REQUEST_URL = "http://packers.netau.net/Booking.php";
    private Map<String, String> params;

    public BookingRequest(String strsource, String strdestination, String strpickupdate, Response.Listener<String> listener) {
        super(Method.POST, BOOKING_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("source",strsource);
        params.put("destination",strdestination);
        params.put("pickupdate",strpickupdate);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
