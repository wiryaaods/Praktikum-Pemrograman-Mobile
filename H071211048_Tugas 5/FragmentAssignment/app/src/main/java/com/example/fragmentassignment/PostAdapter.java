package com.example.fragmentassignment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private ArrayList<Post> userInputList;
    private Context context;

    public PostAdapter(Context context, ArrayList<Post> userInputList) {
        this.context = context;
        this.userInputList = userInputList;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        Post post1 = userInputList.get(position);
        holder.setData(post1);
    }

    @Override
    public int getItemCount() {
        return userInputList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView caption;
        public ImageView postt, profil;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            caption = itemView.findViewById(R.id.caption);
            postt = itemView.findViewById(R.id.post);
            profil = itemView.findViewById(R.id.profil);
        }

        public void setData(Post post) {
            caption.setText(post.getCaption());
            postt.setImageURI(post.getPost());
            profil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ProfileActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}

