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

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.renatoawk.diary.R;
import com.renatoawk.diary.model.Note;
import com.renatoawk.diary.model.Session;
import com.renatoawk.diary.util.Adapter;
import com.renatoawk.diary.util.Time;

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
        recyclerView = findViewById(R.id.recycler_notes);
        //// teste
        Session.user.getNotes().add(new Note(0, "teste0",0, new Time(), new Time()));
        Session.user.getNotes().add(new Note(0, "teste1",0, new Time(), new Time()));
        Session.user.getNotes().add(new Note(0, "teste2",0, new Time(), new Time()));
        Session.user.getNotes().add(new Note(0, "teste3",0, new Time(), new Time()));
        Session.user.getNotes().add(new Note(0, "teste4",0, new Time(), new Time()));
        Session.user.getNotes().add(new Note(0, "teste5",0, new Time(), new Time()));
        Session.user.getNotes().add(new Note(0, "teste6",0, new Time(), new Time()));
        Session.user.getNotes().add(new Note(0, "teste7",0, new Time(), new Time()));
        Session.user.getNotes().add(new Note(0, "teste8",0, new Time(), new Time()));
        Session.user.getNotes().add(new Note(0, "teste9",0, new Time(), new Time()));
        //// fim do teste
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new Adapter(Session.user.getNotes());
        recyclerView.setAdapter(adapter);
    }
}
