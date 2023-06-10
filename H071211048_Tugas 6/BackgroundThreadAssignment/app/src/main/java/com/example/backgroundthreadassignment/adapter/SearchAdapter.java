package com.example.backgroundthreadassignment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.backgroundthreadassignment.Profile;
import com.example.backgroundthreadassignment.R;
import com.example.backgroundthreadassignment.model.Post;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private ArrayList<Post> postList = new ArrayList<>();

    public void setPostList(ArrayList<Post> postList) {
        if (this.postList.size() > 0) {
            this.postList.clear();
        }
        this.postList.addAll(postList);
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(postList.get(position).getProfile())
                .centerCrop()
                .into(holder.photoProfile);
        holder.name.setText(postList.get(position).getFullName());
        holder.uname.setText(postList.get(position).getUserName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    Post clickedPost = postList.get(clickedPosition);
                    Intent intent = new Intent(holder.itemView.getContext(), Profile.class);
                    intent.putExtra(Profile.EXTRA_PROFILE, clickedPost);
                    holder.itemView.getContext().startActivity(intent);
                }
            }
        });
    }

//    @Override
//    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position)  {
//        Glide.with(holder.itemView)
//                .load(postList.get(position).getProfile())
//                .centerCrop()
//                .into(holder.photoProfile);
//        holder.name.setText(postList.get(position).getFullName());
//        holder.uname.setText(postList.get(position).getUserName());
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(holder.itemView.getContext(), Profile.class);
//                intent.putExtra(Profile.EXTRA_PROFILE, postList.get(position));
//                holder.itemView.getContext().startActivity(intent);
//            }
//        });
//
//    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, uname;
        public ImageView photoProfile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.fullname);
            uname = itemView.findViewById(R.id.username);
            photoProfile = itemView.findViewById(R.id.profil);
        }
    }
}
