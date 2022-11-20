package com.example.studyeasy;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public  class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder>{
    public Object notifyDataSetChanged;

    Context context;
    ArrayList<Notes> list;


    public ListAdapter(Context context, ArrayList<Notes> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View v = LayoutInflater.from(context).inflate((R.layout.userentry),parent, false);
      return new ListAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
  Notes notes = list.get(position);
  holder.title.setText(notes.getTitle());
  holder.note.setText(notes.getNote());
    }

    @Override
    public int getItemCount() {

        return list.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

    TextView title, note;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.textName);
            note= itemView.findViewById(R.id.note);
            RecyclerViewInterface recyclerViewInterface = null;
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    if(recyclerViewInterface != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemLongClick(pos);
                        }
                    }
                    return true;

                }
            });
        }



    }
    }
