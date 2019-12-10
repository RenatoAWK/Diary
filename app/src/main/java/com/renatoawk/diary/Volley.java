package com.renatoawk.diary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.renatoawk.diary.gui.ProgressBarDialog;
import com.renatoawk.diary.gui.NotesActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class Volley {
    public static void requestLogin(final Context context, final Map<String, String> map, User user){
        final ProgressBarDialog progressBarDialog = new ProgressBarDialog(context);
        progressBarDialog.openDialog();

        RequestQueue requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(context);
        final String url = "https://diary-node.herokuapp.com/user";
        Response.Listener responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.has("status")){
                        if (jsonObject.get("status").equals("not OK")){
                            progressBarDialog.closeDialog();
                            Toast.makeText(context, "not OK",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "not OK error",Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        User user = new User();
                        user.setName(jsonObject);
                        user.setID(jsonObject);
                        user.setEmail(jsonObject);
                        user.setPassword(jsonObject);
                        user.setNotify(jsonObject);
                        user.setTime(jsonObject);
                        user.setTheme(jsonObject);
                        Session.user = user;
                        progressBarDialog.closeDialog();
                        ((Activity) context).finish();
                        Intent notesAcitivity = new Intent(context, NotesActivity.class);
                        context.startActivity(notesAcitivity);

                    }

                } catch (JSONException e) {
                    progressBarDialog.closeDialog();
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();

                }
            }


        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(),Toast.LENGTH_LONG).show();
                progressBarDialog.closeDialog();

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


    public static void requestSignUp(final Context context, final Map<String, String> map, final User user){
        final ProgressBarDialog progressBarDialog = new ProgressBarDialog(context);
        progressBarDialog.openDialog();

        RequestQueue requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(context);
        String url = "https://diary-node.herokuapp.com/user";
        Response.Listener responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.has("status")){
                        if (jsonObject.get("status").equals("not OK")){
                            progressBarDialog.closeDialog();
                            Toast.makeText(context, "not OK",Toast.LENGTH_SHORT).show();

                        } else if (jsonObject.get("status").equals("OK")){
                            progressBarDialog.closeDialog();
                            Session.user = user;
                            ((Activity) context).finish();
                        } else {
                            Toast.makeText(context, "not OK error",Toast.LENGTH_SHORT).show();

                        }

                    }

                } catch (JSONException e) {
                    progressBarDialog.closeDialog();
                    Toast.makeText(context, "Connection error", Toast.LENGTH_LONG).show();

                }
            }

        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBarDialog.closeDialog();
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
