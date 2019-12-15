package com.renatoawk.diary.gui;

import androidx.annotation.NonNull;
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
import com.renatoawk.diary.Time;

import java.text.DateFormat;
import java.util.Calendar;

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
                    time.getMonth(),
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
            Toast.makeText(this, "Enviar..", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}
