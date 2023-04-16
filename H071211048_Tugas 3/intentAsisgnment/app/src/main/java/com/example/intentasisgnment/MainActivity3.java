package com.example.intentasisgnment;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        String fullname = getIntent().getStringExtra("fullname");
        String username = getIntent().getStringExtra("username");
        Uri profilImage = getIntent().getParcelableExtra("profil");
        String capt = getIntent().getStringExtra("caption");
        Uri post = getIntent().getParcelableExtra("post");

        TextView tv1 = findViewById(R.id.fullName);
        TextView tv2 = findViewById(R.id.userName);
        TextView tv8 = findViewById(R.id.caption);
        ImageView iv = findViewById(R.id.profil);
        ImageView iv2 = findViewById(R.id.post);

        tv1.setText(fullname);
        tv2.setText(username);
        tv8.setText(capt);
        iv.setImageURI(profilImage);
        iv2.setImageURI(post);
    }
}