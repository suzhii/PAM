package com.example.jagadeeshkumar.packers;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jagadeesh Kumar on 17-06-2016.
 */
public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://packers.netau.net/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String strfname, String strlname, String struname, String strdob, String stremail, String strpass, String strmobile, Response.Listener<String> listener){

        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("firstname",strfname);
        params.put("lastname",strlname);
        params.put("username",struname);
        params.put("dateofbirth",strdob);
        params.put("email",stremail);
        params.put("password",strpass);
        params.put("mobilenumber",strmobile);


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
