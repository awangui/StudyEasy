package com.example.studyeasy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class new_task extends AppCompatActivity {
//    private static final String TAG = "new_Task";
    DatabaseReference dbRef;
    Button save;
    EditText createTask;
    boolean isTaskEmpty = false;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_task);

        //sends task to database
        save = findViewById(R.id.newTaskButton);
        //find input
        createTask=findViewById(R.id.newTaskText);
        dbRef= FirebaseDatabase.getInstance().getReference().child("Tasks");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTask=findViewById(R.id.newTaskText);
                //convert user data to string
                String task = createTask.getText().toString();

                //instantiation
               ToDoModel tasks = new ToDoModel(task);
                isTaskEmpty = checkAllFields();
                //pushing to database
                if (isTaskEmpty) {
                    dbRef.push().setValue(tasks);

                    Toast.makeText(new_task.this, "Task saved", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(new_task.this, MainActivity2.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    finish();
                }
            }
        });

    }
    private boolean checkAllFields(){
        if (createTask.length()==0){
            createTask.setError("Please input text");
            return false;
        }
        return  true;
    }
}
