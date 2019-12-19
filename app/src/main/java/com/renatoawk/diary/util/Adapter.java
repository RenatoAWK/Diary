package com.renatoawk.diary.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.renatoawk.diary.R;
import com.renatoawk.diary.model.Note;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<Note> data;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            title = itemView.findViewById(R.id.title_recycler_item);
            text = itemView.findViewById(R.id.text_recycler_item);
        }
    }


    public Adapter(ArrayList<Note> arrayList){
        this.data = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(this.data.get(position).getEdited().getFormatedDate());
        holder.text.setText(this.data.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }
}
