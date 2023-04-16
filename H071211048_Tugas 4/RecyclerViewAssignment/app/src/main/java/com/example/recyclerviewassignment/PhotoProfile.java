package com.example.recyclerviewassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PhotoProfile extends AppCompatActivity {

    ImageView back, profil;
    TextView nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_profile);

        back = findViewById(R.id.back);
        profil = findViewById(R.id.profil);
        nama = findViewById(R.id.nama);

        Chat chat = getIntent().getParcelableExtra("profil");

        nama.setText(chat.getNama());
        profil.setImageResource(chat.getFoto());

    }

    public void back(View view){
        finish();
    }


}