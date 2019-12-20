package com.renatoawk.diary.gui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
    private Note note = null;
    private boolean editMode = false;
    private Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        editText = findViewById(R.id.text_note);
        setUpToolbar();
        editText.requestFocus();

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.NOTE)){
            if (!editMode){
                note = intent.getParcelableExtra(Constants.NOTE);
                menu.findItem(R.id.edit_note).setVisible(true);
                menu.findItem(R.id.delete_note).setVisible(true);
                menu.findItem(R.id.calendar_note).setVisible(false);
                menu.findItem(R.id.time_note).setVisible(false);
                menu.findItem(R.id.add_note).setVisible(false);
                editText.setClickable(false);
                editText.setCursorVisible(false);
                editText.setFocusable(false);
                editText.setFocusableInTouchMode(false);
                editText.setText(note.getText());
            } else {
                menu.findItem(R.id.edit_note).setVisible(false);
                menu.findItem(R.id.delete_note).setVisible(false);
                menu.findItem(R.id.calendar_note).setVisible(true);
                menu.findItem(R.id.time_note).setVisible(true);
                menu.findItem(R.id.add_note).setVisible(true);
                editText.setClickable(true);
                editText.setCursorVisible(true);
                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
                time = note.getEdited();

            }
        }

        return true;

    }


    private void setUpToolbar() {
        toolbar = findViewById(R.id.toolbar_note);
        toolbar.setTitle(time.getFormatedDateTime());
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_note, menu);
        this.menu = menu;
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
                map.put(Constants.NOTE_ATTRIBUTE_TEXT, editText.getText().toString());
                map.put(Constants.NOTE_ATTRIBUTE_EMOTION, "0");
                map.put(Constants.NOTE_ATTRIBUTE_CREATED, time.getTimeStampPostgres());
                map.put(Constants.NOTE_ATTRIBUTE_EDITED, time.getTimeStampPostgres());
                if (editMode){
                    map.put(Constants.NOTE_ATTRIBUTE_ID, String.valueOf(note.getId()));
                    Volley.requestEditNote(NoteActivity.this, map);
                } else {
                    map.put(Constants.NOTE_ATTRIBUTE_ID_USER, String.valueOf(Session.user.getId()));
                    Volley.requestInsertNote(NoteActivity.this, map);
                }


            }
        } else if (item.getItemId() == R.id.edit_note){
            editMode = true;
            onPrepareOptionsMenu(menu);

        } else if (item.getItemId() == R.id.delete_note){
            new AlertDialog.Builder(this)
                    .setMessage(getString(R.string.are_you_sure_you_want_to_delete_this_note))
                    .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Map<String, String> map = new HashMap<>();
                            map.put(Constants.NOTE_ATTRIBUTE_ID, String.valueOf(note.getId()));
                            Volley.requestRemoveNote(NoteActivity.this, map);
                        }
                    }).setNegativeButton(getString(R.string.cancel), null)
                    .show();
        }

        return true;
    }
}
