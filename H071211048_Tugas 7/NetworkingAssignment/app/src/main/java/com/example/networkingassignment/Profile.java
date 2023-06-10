package com.example.networkingassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {

    public static final String EXTRA_USER ="extra_user" ;
    private TextView nama, email, tv_alert;
    private ImageView profil, btn_refresh;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        nama = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        profil = findViewById(R.id.profil);
        tv_alert = findViewById(R.id.tv_alert);
        btn_refresh = findViewById(R.id.btn_refresh);
        progressBar = findViewById(R.id.progress_bar);

        consumeAPI();
    }

    public void consumeAPI(){
        showLoading();
        int id = getIntent().getIntExtra(EXTRA_USER,1);
        Call<UserResponse> client = ApiConfig.getApiService().getSingleUser(id);
        client.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    if(response.body() != null){
                        UserResponse userResponse = response.body().getData();
                        String fullName = userResponse.getFirst_name() + " " +
                                userResponse.getLast_name();
                        nama.setText(fullName);
                        email.setText(userResponse.getEmail());
                        Glide.with(Profile.this)
                                .load(userResponse.getAvatarUrl())
                                .into(profil);
                        hideLoading();
                    }
                } else {
                    if (response.body() != null) {
                        Log.e("MainActivity", "onFailure: " + response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                showAlert();
            }
        });
    }

    public void showAlert(){
        progressBar.setVisibility(View.GONE);
        profil.setVisibility(View.GONE);
        nama.setVisibility(View.GONE);
        email.setVisibility(View.GONE);
        tv_alert.setVisibility(View.VISIBLE);
        btn_refresh.setVisibility(View.VISIBLE);

        tv_alert.setText("Please check your internet connection");

        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consumeAPI();
            }
        });
    }

    public void showLoading(){
        progressBar.setVisibility(View.VISIBLE);
        profil.setVisibility(View.INVISIBLE);
        nama.setVisibility(View.INVISIBLE);
        email.setVisibility(View.INVISIBLE);
        tv_alert.setVisibility(View.GONE);
        btn_refresh.setVisibility(View.GONE);
    }

    private void hideLoading(){
        progressBar.setVisibility(View.GONE);
        profil.setVisibility(View.VISIBLE);
        nama.setVisibility(View.VISIBLE);
        email.setVisibility(View.VISIBLE);
        tv_alert.setVisibility(View.GONE);
        btn_refresh.setVisibility(View.GONE);
    }
}