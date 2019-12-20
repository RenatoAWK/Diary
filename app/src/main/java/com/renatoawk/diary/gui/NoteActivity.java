package com.renatoawk.diary.gui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.renatoawk.diary.R;
import com.renatoawk.diary.model.Note;
import com.renatoawk.diary.model.Session;
import com.renatoawk.diary.util.Constants;
import com.renatoawk.diary.util.Time;
import com.renatoawk.diary.util.Validation;
import com.renatoawk.diary.util.Volley;

import java.util.HashMap;
import java.util.Map;

public class NoteActivity extends AppCompatActivity {
    private Time time = new Time();
    private Toolbar toolbar;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        editText = findViewById(R.id.text_note);
        setUpToolbar();
        editText.requestFocus();

    }

    private void setUpToolbar() {
        toolbar = findViewById(R.id.toolbar_note);
        toolbar.setTitle(time.getFormatedDateTime());
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.cancel_note){
            finish();
        } else if (item.getItemId() == R.id.calendar_note){

            new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    time.setYear(year);
                    time.setMonth(month);
                    time.setDayOfMonth(dayOfMonth);
                    setUpToolbar();
                }
            },
                    time.getYear(),
                    time.getMonth()-1,
                    time.getDayOfMonth())
                    .show();

        } else if (item.getItemId() == R.id.time_note){

            new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    time.setHour(hourOfDay);
                    time.setMinute(minute);
                    setUpToolbar();
                }
            },      time.getHour(),
                    time.getMinute(),
                    true)
                    .show();


        } else if (item.getItemId() == R.id.add_note){
            if (Validation.isBlank(editText.getText().toString())){
                new AlertDialog.Builder(getApplicationContext())
                        .setMessage(getString(R.string.empty_field))
                        .setPositiveButton(getString(R.string.OK), null)
                        .show();
            } else {
                Map<String, String> map = new HashMap<>();
                map.put(Constants.NOTE_ATTRIBUTE_ID_USER, String.valueOf(Session.user.getId()));
                map.put(Constants.NOTE_ATTRIBUTE_TEXT, editText.getText().toString());
                map.put(Constants.NOTE_ATTRIBUTE_EMOTION, "0");
                map.put(Constants.NOTE_ATTRIBUTE_CREATED, time.getTimeStampPostgres());
                map.put(Constants.NOTE_ATTRIBUTE_EDITED, time.getTimeStampPostgres());
                Volley.requestInsertNote(NoteActivity.this, map);


            }
        }

        return true;
    }
}
