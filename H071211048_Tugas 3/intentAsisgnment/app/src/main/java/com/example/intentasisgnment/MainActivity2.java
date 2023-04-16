package com.example.intentasisgnment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {

    private ImageView post;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    TextView caption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        caption = findViewById(R.id.caption);
        post = findViewById(R.id.post);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                post.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void upload(View view) {

        String capt = caption.getText().toString();
        Uri profilImage =  getIntent().getParcelableExtra("profil");
        String fName = getIntent().getStringExtra("fullname");
        String uName = getIntent().getStringExtra("username");

        if (imageUri == null) {
            Toast.makeText(this, "please select an image", Toast.LENGTH_SHORT).show();
        } else {
            Intent tes = new Intent(this, MainActivity3.class);
            tes.putExtra("caption", capt);
            tes.putExtra("post", imageUri);
            tes.putExtra("fullname", fName);
            tes.putExtra("username", uName);
            tes.putExtra("profil", profilImage);
            startActivity(tes);
        }
    }
}