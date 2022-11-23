package com.example.studyeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;
    private List<ToDoModel> taskList;
    DatabaseReference tasksDbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tasksDbRef= FirebaseDatabase.getInstance().getReference("Tasks");
        taskList=new ArrayList<>();
        tasksRecyclerView=findViewById(R.id.tasksRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        tasksRecyclerView.setLayoutManager(layoutManager);
        tasksAdapter = new ToDoAdapter(this);
        tasksRecyclerView.setAdapter(tasksAdapter);
        new  ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(tasksRecyclerView);
        tasksDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ToDoModel tasks = dataSnapshot.getValue(ToDoModel.class);
                    taskList.add(tasks);
                }
                Collections.reverse(taskList);
                tasksAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        tasksAdapter.setTasks(taskList);

}

    public void openCreateTask(View view) {
        startActivity(new Intent(MainActivity2.this, new_task.class));
        finish();
    }
    ItemTouchHelper.SimpleCallback itemTouchHelperCallback= new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        taskList.remove(viewHolder.getAdapterPosition());
        tasksAdapter.notifyDataSetChanged();
        }
    };
}
