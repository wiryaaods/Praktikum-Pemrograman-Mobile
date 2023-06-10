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
import java.util.Collections;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{

    private ArrayList<Post> postList;
    Context context;

    public PostAdapter(ArrayList<Post> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        holder.caption.setText(postList.get(position).getCaption());
        holder.username.setText(postList.get(position).getUserName());
        Glide.with(holder.itemView).load(postList.get(position).getProfile())
                .centerCrop().into(holder.profil);
        Glide.with(holder.itemView).load(postList.get(position).getUpload())
                .centerCrop().into(holder.post);

        holder.profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), Profile.class);
                intent.putExtra(Profile.EXTRA_PROFILE, postList.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), Profile.class);
                intent.putExtra(Profile.EXTRA_PROFILE, postList.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView caption,username;
        public ImageView post, profil;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            caption = itemView.findViewById(R.id.caption);
            post = itemView.findViewById(R.id.post);
            username = itemView.findViewById(R.id.username);
            profil = itemView.findViewById(R.id.profil);
        }
    }
}
