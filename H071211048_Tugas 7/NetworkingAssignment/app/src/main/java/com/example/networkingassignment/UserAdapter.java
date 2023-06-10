package com.example.networkingassignment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<UserResponse> data;

    public UserAdapter (List<UserResponse> data){
        this.data=data;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        UserResponse userResponse = data.get(position);
        holder.setData(userResponse);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView profil;
        private TextView name, email;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profil = itemView.findViewById(R.id.profil);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
        }
        public void setData(UserResponse userResponse) {
            String fullName = userResponse.getFirst_name() + " " + userResponse.getLast_name();
            Glide.with(itemView.getContext()).
                    load(userResponse.getAvatarUrl()).into(profil);
            name.setText(fullName);
            email.setText(userResponse.getEmail());
            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(itemView.getContext(), Profile.class);
                intent.putExtra(Profile.EXTRA_USER, userResponse.getId());
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
