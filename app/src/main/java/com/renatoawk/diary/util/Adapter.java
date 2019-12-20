package com.renatoawk.diary.util;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.renatoawk.diary.R;
import com.renatoawk.diary.gui.NoteActivity;
import com.renatoawk.diary.gui.NotesActivity;
import com.renatoawk.diary.model.Note;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private final Context context;
    private ArrayList<Note> data;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView sub;
        private TextView text;

        public ViewHolder(@NonNull View itemView, final Context context, final ArrayList<Note> data) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NoteActivity.class);
                    intent.putExtra(Constants.NOTE, data.get(getAdapterPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            });

            title = itemView.findViewById(R.id.title_recycler_item);
            sub = itemView.findViewById(R.id.subtitle_recycler_item);
            text = itemView.findViewById(R.id.text_recycler_item);
        }
    }


    public Adapter(ArrayList<Note> arrayList, Context context){
        this.context = context;
        this.data = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view, context, data);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(this.data.get(position).getEdited().getFormatedDateTime());
        holder.sub.setText(context.getString(R.string.created_on) + this.data.get(position).getCreated().getFormatedDateTime());
        holder.text.setText(this.data.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }
}
