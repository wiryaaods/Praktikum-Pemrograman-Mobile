package com.example.networkingassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private ProgressBar progressBar;
    private TextView tv_alert;
    private ImageView btn_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);
        progressBar = findViewById(R.id.progress_bar);
        tv_alert =findViewById(R.id.tv_alert);
        btn_refresh = findViewById(R.id.btn_refresh);
        rv.setLayoutManager(new LinearLayoutManager(this));

        consumeAPI();
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                consumeAPI();
            }
        });

    }
    public void consumeAPI() {
        showLoading();
        Call<DataResponse> client = ApiConfig.getApiService().getUser("12");
        client.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                if (response.isSuccessful()){
                    if (response != null) {
                        List<UserResponse> data = response.body().getData();
                        UserAdapter userAdapter = new UserAdapter(data);
                        rv.setAdapter(userAdapter);
                        hideLoading();
                    }
                }else{
                    if(response.body() !=null){
                        Log.e("Main Activity", "onFailure: "+ response.message());
                    }
                }
            }
            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                showAlert();
            }
        });
    }
    public void showAlert() {
        progressBar.setVisibility(View.INVISIBLE);
        tv_alert.setVisibility(View.VISIBLE);
        btn_refresh.setVisibility(View.VISIBLE);

    }

    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
        rv.setVisibility(View.VISIBLE);
        tv_alert.setVisibility(View.INVISIBLE);
        btn_refresh.setVisibility(View.INVISIBLE);
    }

    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        rv.setVisibility(View.INVISIBLE);
        btn_refresh.setVisibility(View.INVISIBLE);
        tv_alert.setVisibility(View.INVISIBLE);
    }

}