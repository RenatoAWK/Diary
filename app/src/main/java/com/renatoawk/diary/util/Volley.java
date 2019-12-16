package com.renatoawk.diary.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.renatoawk.diary.Constants_url;
import com.renatoawk.diary.R;
import com.renatoawk.diary.gui.NotesActivity;
import com.renatoawk.diary.gui.ProgressBarDialog;
import com.renatoawk.diary.model.Session;
import com.renatoawk.diary.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class Volley {
    public static void requestLogin(final Context context, final Map<String, String> map, User user){
        final ProgressBarDialog progressBarDialog = new ProgressBarDialog(context);
        progressBarDialog.openDialog();

        RequestQueue requestQueue = com.android.volley.toolbox.Volley.newRequestQueue(context);
        final String url = Constants_url.URL_LOGIN;
        Response.Listener responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressBarDialog.closeDialog();
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.has(Constants.STATUS)){
                        if (jsonObject.get(Constants.STATUS).equals(Constants.INTERNAL_SERVER_ERROR)){
                            new AlertDialog.Builder(context)
                                    .setMessage(context.getString(R.string.request_error))
                                    .setPositiveButton(context.getString(R.string.OK), null)
                                    .show();

                        } else if (jsonObject.get(Constants.STATUS).equals(Constants.NOT_ACCETABLE)){
                            new AlertDialog.Builder(context)
                                    .setMessage(context.getString(R.string.unknown_error))
                                    .setPositiveButton(context.getString(R.string.OK), null)
                                    .show();
                        } else if (jsonObject.get(Constants.STATUS).equals(Constants.OK)){
                            User user = new User();
                            user.setName(jsonObject.getJSONArray(Constants.RESULTS).getJSONObject(0));
                            user.setID(jsonObject.getJSONArray(Constants.RESULTS).getJSONObject(0));
                            user.setEmail(jsonObject.getJSONArray(Constants.RESULTS).getJSONObject(0));
                            user.setNotify(jsonObject.getJSONArray(Constants.RESULTS).getJSONObject(0));
                            user.setTime(jsonObject.getJSONArray(Constants.RESULTS).getJSONObject(0));
                            user.setTheme(jsonObject.getJSONArray(Constants.RESULTS).getJSONObject(0));
                            user.setPassword(map.get(Constants.USER_ATTRIBUTE_PASSWORD));
                            Session.user = user;
                            ((Activity) context).finish();
                            Intent notesAcitivity = new Intent(context, NotesActivity.class);
                            context.startActivity(notesAcitivity);

                        }

                    } else {
                        new AlertDialog.Builder(context)
                                .setMessage(context.getString(R.string.error))
                                .setPositiveButton(context.getString(R.string.OK), null)
                                .show();
                    }

                } catch (JSONException e) {
                    new AlertDialog.Builder(context)
                            .setMessage(context.getString(R.string.connection_error))
                            .setPositiveButton(context.getString(R.string.OK), null)
                            .show();
                }
            }


        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBarDialog.closeDialog();
                new AlertDialog.Builder(context)
                        .setMessage(context.getString(R.string.error))
                        .setPositiveButton(context.getString(R.string.OK), null)
                        .show();

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
        String url = Constants_url.URL_USER;
        Response.Listener responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressBarDialog.closeDialog();
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.has(Constants.STATUS)){
                        if (jsonObject.get(Constants.STATUS).equals(Constants.INTERNAL_SERVER_ERROR)) {
                            new AlertDialog.Builder(context)
                                    .setMessage(context.getString(R.string.request_error))
                                    .setPositiveButton(context.getString(R.string.OK), null)
                                    .show();
                        } else if (jsonObject.get(Constants.STATUS).equals(Constants.NOT_ACCETABLE)){
                            new AlertDialog.Builder(context)
                                    .setMessage(context.getString(R.string.unknown_error))
                                    .setPositiveButton(context.getString(R.string.OK), null)
                                    .show();
                        } else if (jsonObject.get(Constants.STATUS).equals(Constants.CREATED)){
                            progressBarDialog.closeDialog();
                            Session.user = user;
                            ((Activity) context).finish();
                        } else {
                            new AlertDialog.Builder(context)
                                    .setMessage(context.getString(R.string.error))
                                    .setPositiveButton(context.getString(R.string.OK), null)
                                    .show();
                        }

                    } else {
                        new AlertDialog.Builder(context)
                                .setMessage(context.getString(R.string.error))
                                .setPositiveButton(context.getString(R.string.OK), null)
                                .show();
                    }

                } catch (JSONException e) {
                    new AlertDialog.Builder(context)
                            .setMessage(context.getString(R.string.connection_error))
                            .setPositiveButton(context.getString(R.string.OK), null)
                            .show();
                }
            }

        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBarDialog.closeDialog();
                new AlertDialog.Builder(context)
                        .setMessage(context.getString(R.string.error))
                        .setPositiveButton(context.getString(R.string.OK), null)
                        .show();
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
