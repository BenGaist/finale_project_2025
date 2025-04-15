package com.example.finale_project_2025;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> noteList;

    public NoteAdapter(List<Note> noteList) {
        this.noteList = noteList;
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTextView, motivationTextView, scoreTextView;

        public NoteViewHolder(View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            motivationTextView = itemView.findViewById(R.id.motivationTextView);
            scoreTextView = itemView.findViewById(R.id.scoreTextView);
        }
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_result, parent, false);  // Still using item_result.xml
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.usernameTextView.setText("Username: " + note.getUsername());
        holder.motivationTextView.setText(note.getSentence());
        holder.scoreTextView.setText("Score: " + note.getPercent() + "%");
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
}
