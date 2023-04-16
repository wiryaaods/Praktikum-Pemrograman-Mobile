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

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private ArrayList<Chat> chats;

    public ChatAdapter (ArrayList<Chat> chats){
        this.chats=chats;
    }

    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder holder, int position) {
        Chat chat1 = chats.get(position);
        holder.setData(chat1);
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_nama;
        public TextView tv_pesan;
        public ImageView iv;
        public TextView time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nama = itemView.findViewById(R.id.nama);
            tv_pesan = itemView.findViewById(R.id.msg);
            iv = itemView.findViewById(R.id.profil);
            time = itemView.findViewById(R.id.time);

        }
        public void setData(Chat chat){
            Chat chat1 = new Chat(chat.getNama(), chat.getPesan(), chat.getTime(), chat.getFoto(),
                    chat.getNoTelp(), chat.getStatus(), chat.getDate());
            tv_nama.setText(chat.getNama());
            tv_pesan.setText(chat.getPesan());
            iv.setImageResource(chat.getFoto());
            time.setText(chat.getTime());
            iv.setOnClickListener(view -> {
                Intent sent = new Intent(iv.getContext(), PhotoProfile.class);
                sent.putExtra("profil", chat1);
                iv.getContext().startActivity(sent);
            });
            itemView.setOnClickListener(view -> {
                Intent kirim = new Intent(itemView.getContext(), MainActivity2.class);
                kirim.putExtra("nama", chat1);
                itemView.getContext().startActivity(kirim);;
            });
        }

    }

}
