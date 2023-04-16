package com.example.recyclerviewassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    ImageView fotoprofil;
    TextView nama, notelp, status, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fotoprofil = findViewById(R.id.profil);
        nama = findViewById(R.id.nama);
        notelp = findViewById(R.id.notelp);
        status = findViewById(R.id.status);
        date = findViewById(R.id.date);

        Chat chat = getIntent().getParcelableExtra("profile");

        fotoprofil.setImageResource(chat.getFoto());
        nama.setText(chat.getNama());
        notelp.setText(chat.getNoTelp());
        status.setText(chat.getStatus());
        date.setText(chat.getDate());

        fotoprofil.setOnClickListener(view -> {
            Intent sent = new Intent(fotoprofil.getContext(), PhotoProfile.class);
            sent.putExtra("profil", chat);
            startActivity(sent);
        });


    }

    public void back(View view){
        finish();
    }
}