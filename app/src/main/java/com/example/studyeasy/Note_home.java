package com.example.studyeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Note_home extends AppCompatActivity implements  RecyclerViewInterface{
    ListView myListView;
    List<Notes> notesList;
    RecyclerView recyclerView;
    ArrayList<Notes> list;
    ListAdapter adapter;
    DatabaseReference notesDbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_home);
//        myListView = findViewById(R.id.myListView);
//        notesList = new ArrayList<>();
        notesDbRef = FirebaseDatabase.getInstance().getReference("Notes");
        recyclerView= findViewById(R.id.recycleview);
        list = new ArrayList<>();

        adapter = new ListAdapter(this, list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notesDbRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Notes notes = dataSnapshot.getValue(Notes.class);
                    list.add(notes);
                }
                Object notifyDataSetChanged = adapter.notifyDataSetChanged;
            }

//

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

    public void openCreateNote(View view) {
        startActivity(new Intent(Note_home.this, MainActivity.class));
        finish();
    }



    @Override
    public void onItemLongClick(int position) {
        list.remove(position);
        adapter.notifyItemRemoved(position);
    }

//    public void openUploadNote(View view) {
//        startActivity(new Intent(Note_home.this, uploadNote.class));
//        finish();
//
}