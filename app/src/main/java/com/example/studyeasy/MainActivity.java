package com.example.studyeasy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    //authentication

     DatabaseReference dbRef;
     Button save;
     EditText createTitle;
     EditText createNote;
     boolean isNoteChecked = false;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        //sends notes to database upon clicking save
        save = findViewById(R.id.addNote);
        //find inputs by id
        createTitle = findViewById(R.id.createTitle);
        createNote = findViewById(R.id.createNote);
        dbRef=FirebaseDatabase.getInstance().getReference().child("Notes");
        //initialize Firebase and get instance
//      FirebaseDatabase database = FirebaseDatabase.getInstance();



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //find inputs by id

                createTitle = findViewById(R.id.createTitle);
                createNote = findViewById(R.id.createNote);
                //convert user data to string

                String title = createTitle.getText().toString();
                String note = createNote.getText().toString();


                //instantiation
                Notes notes = new Notes(title, note);
                isNoteChecked = checkAllFields();//checking if notes is empty
                //sending data to the database
                if (isNoteChecked){
                dbRef.push().setValue(notes);
                //implementing the back button to function
                Toast.makeText(MainActivity.this, "Note saved", Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this, Note_home.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                }

            }

        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(MainActivity.this, Note_home.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todo_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                Log.d(TAG, "Add a new task");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private boolean checkAllFields(){
        if (createNote.length()==0){
            createNote.setError("Please input text");
            return false;
        }
        return  true;
    }
}

//    public void runSaveNote(View view) {
//        Toast.makeText(getApplicationContext(),"Note saved",Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(MainActivity.this, Note_home.class));
//        finish();
//    }
//}