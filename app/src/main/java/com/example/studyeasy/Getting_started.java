package com.example.studyeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class Getting_started extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getting_started);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Get Started");
    }

    public void openNotes(View view) {
        startActivity(new Intent(Getting_started .this, uploadNote.class));
        finish();
    }
}