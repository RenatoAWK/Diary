package com.renatoawk.diary;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class Volley {
    public static void requestLogin(final Context context, final Map<String, String> map){
        RequestQueue requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(context);
        String url = "https://diary-node.herokuapp.com/user";
        Response.Listener responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.has("status")){
                        if (jsonObject.get("status").equals("not OKAY")){
                            Toast.makeText(context, "not OKAY",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "OKAY",Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        User user = new User();
                        user.setName(jsonObject.getString("__name"));
                        user.setId(jsonObject.getInt("__id"));
                        user.setEmail(jsonObject.getString("__email"));
                        user.setPassword(jsonObject.getString("__password"));
                        user.setNotify(jsonObject.getBoolean("__notify"));
                        user.setHour(jsonObject.getString("__hour"));
                        user.setTheme(jsonObject.getInt("__theme"));
                        Session.user = user;
                    }

                } catch (JSONException e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }


        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(),Toast.LENGTH_LONG).show();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responseListener, errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };

        requestQueue.add(stringRequest);
    }


    public static void requestSignUp(final Context context, final Map<String, String> map){

        RequestQueue requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(context);
        String url = "https://diary-node.herokuapp.com/user";
        Response.Listener responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.has("status")){
                        if (jsonObject.get("status").equals("not OKAY")){
                            Toast.makeText(context, "not OKAY",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "OKAY",Toast.LENGTH_SHORT).show();
                        }

                    }

                } catch (JSONException e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(),Toast.LENGTH_LONG).show();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responseListener, errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };

        requestQueue.add(stringRequest);

    }
}
