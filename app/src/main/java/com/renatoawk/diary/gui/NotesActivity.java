package com.renatoawk.diary.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.renatoawk.diary.R;
import com.renatoawk.diary.model.Note;
import com.renatoawk.diary.model.Session;
import com.renatoawk.diary.util.Adapter;
import com.renatoawk.diary.util.Time;
import com.renatoawk.diary.util.Volley;

import java.util.HashMap;
import java.util.Map;

public class NotesActivity extends AppCompatActivity {
    private BottomAppBar bottomAppBar;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private Adapter adapter;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == 16908332){
            BottomNavigationDrawerFragment bottomNavigationDrawerFragment = new BottomNavigationDrawerFragment(this);
            bottomNavigationDrawerFragment.show(getSupportFragmentManager(), bottomNavigationDrawerFragment.getTag());

        } else if (item.getItemId() == R.id.app_bar_settings){
            Toast.makeText(this, "Pegou o clique no settings", Toast.LENGTH_SHORT).show();

        }  else if (item.getItemId() == R.id.app_bar_search){
            Toast.makeText(this, "Pegou o clique no search", Toast.LENGTH_SHORT).show();

        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        bottomAppBar = findViewById(R.id.app_bar);
        fab = findViewById(R.id.fab);
        setSupportActionBar(bottomAppBar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent noteActivity = new Intent(getApplicationContext(), NoteActivity.class);
                startActivity(noteActivity);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        String url_query = "?id_user="+ Session.user.getId();
        adapter = new Adapter(Session.user.getNotes(), getApplicationContext());
        recyclerView = findViewById(R.id.recycler_notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
        Volley.requestNotes(NotesActivity.this, url_query, adapter);


    }
}
