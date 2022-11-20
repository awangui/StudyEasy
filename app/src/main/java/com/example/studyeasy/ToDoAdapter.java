package com.example.studyeasy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {
private List<ToDoModel> todolist;
private MainActivity2 activity;

public  ToDoAdapter(MainActivity2 activity){

    this.activity = activity;
        }
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
    View itemView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.hello, parent, false);
    return new ViewHolder(itemView);
}
public void onBindViewHolder(ViewHolder holder, int position){
    ToDoModel item = todolist.get(position);
    holder.task.setText(item.getTask());
//    holder.task.setChecked(toBoolean(item.getStatus()));
}

    @Override
    public int getItemCount() {
        return todolist.size();
    }

    private boolean toBoolean(int n){
    return n!=0;
}
    public  void setTasks(List<ToDoModel>todolist){
        this.todolist =todolist;
        notifyDataSetChanged();
    }
public static class ViewHolder extends RecyclerView.ViewHolder{
    CheckBox task;
    ViewHolder(View view){
    super(view);
    task = view.findViewById(R.id.todoCheckBox);
    }
}
}
