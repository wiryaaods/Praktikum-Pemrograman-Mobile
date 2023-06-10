package com.example.backgroundthreadassignment;

import static android.content.Intent.EXTRA_USER;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.backgroundthreadassignment.fragment.HomeFragment;
import com.example.backgroundthreadassignment.fragment.ProfileFragment;
import com.example.backgroundthreadassignment.model.Post;

public class Profile extends AppCompatActivity {

    public static final String EXTRA_PROFILE = "extra_profile";
    private ProgressBar progressBar;
    private TextView username, fullname;
    private ImageView profil;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        progressBar = findViewById(R.id.progressBar);
        username = findViewById(R.id.uname);
        fullname = findViewById(R.id.name);
        profil = findViewById(R.id.profil);
        linearLayout = findViewById(R.id.layout);


        LoadProfileTask loadProfileTask = new LoadProfileTask();
        loadProfileTask.execute();
    }
    private class LoadProfileTask extends AsyncTask<Void, Void, Post> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.GONE);
        }

        @Override
        protected Post doInBackground(Void... voids) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Post post = getIntent().getParcelableExtra(EXTRA_PROFILE);
            return post;
        }

        @Override
        protected void onPostExecute(Post post) {
            progressBar.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
            fullname.setText(post.getFullName());
            username.setText(post.getUserName());

            Glide.with(Profile.this)
                    .load(post.getProfile())
                    .into(profil);
        }
    }
}