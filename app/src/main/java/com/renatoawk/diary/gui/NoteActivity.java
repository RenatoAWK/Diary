package com.renatoawk.diary.gui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.renatoawk.diary.R;
import com.renatoawk.diary.Time;

public class NoteActivity extends AppCompatActivity {
    private Time time = new Time();
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        toolbar = findViewById(R.id.toolbar_note);
        toolbar.setTitle(time.getFormatedDateTime());
        setSupportActionBar(toolbar);


    }

    private void setUpTitle() {
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
            Toast.makeText(this, "chamar date picker", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.add_note){
            Toast.makeText(this, "Enviar..", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}
