package com.example.recyclerviewassignment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class rChatAdapter extends RecyclerView.Adapter<rChatAdapter.ViewHolder> {
    private ArrayList<Chat> chats;

    public rChatAdapter (ArrayList<Chat> chats){
        this.chats=chats;
    }

    @NonNull
    @Override
    public rChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_chat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull rChatAdapter.ViewHolder holder, int position) {
        Chat chat1= chats.get(position);
        holder.setData(chat1);

    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView time, msg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.tv_time);
            msg = itemView.findViewById(R.id.tv_chat);

        }
        public void setData(Chat chat){

            time.setText(chat.getTime());
            msg.setText(chat.getPesan());
        }
    }
}
