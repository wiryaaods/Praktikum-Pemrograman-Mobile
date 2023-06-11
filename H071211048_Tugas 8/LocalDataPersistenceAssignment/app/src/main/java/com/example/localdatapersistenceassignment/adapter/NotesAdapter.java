package com.example.localdatapersistenceassignment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.localdatapersistenceassignment.AddNotes;
import com.example.localdatapersistenceassignment.Notes;
import com.example.localdatapersistenceassignment.R;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private ArrayList<Notes> note;
    private final ClickListener clickListener;

    public NotesAdapter(ArrayList<Notes> note, ClickListener clickListener) {
        this.note = note;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes, parent, false);
        return new ViewHolder(view);
    }

    public void updateData(ArrayList<Notes> newData) {
        note.clear();
        note.addAll(newData);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        Notes notes = note.get(position);
        holder.setData(notes);
    }

    @Override
    public int getItemCount() {
        return note.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle, tvDescription, tvCreatedAt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvCreatedAt = itemView.findViewById(R.id.tv_createdAt);
        }

        public void setData(Notes notes) {
            tvTitle.setText(notes.getTitle());
            tvDescription.setText(notes.getDescription());
            tvCreatedAt.setText(notes.getTimestamp());
            itemView.setOnClickListener(view -> clickListener.onItemClicked(notes));
        }
    }
    public interface ClickListener {
        void onItemClicked(Notes note);
    }
}
