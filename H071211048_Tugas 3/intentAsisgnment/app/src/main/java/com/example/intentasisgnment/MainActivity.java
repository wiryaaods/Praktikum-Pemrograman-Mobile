package com.example.intentasisgnment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private CircleImageView profilImage;
    private static final int PICK_IMAGE =1 ;
    Uri imageUri;
    TextInputLayout fullName, username;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profilImage = (CircleImageView) findViewById(R.id.profil);
        profilImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery,1);
            }
        });
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profilImage.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void submit (View view) {

        TextInputLayout fullName = findViewById(R.id.fullName);
        String fName = String.valueOf(fullName.getEditText().getText());

        TextInputLayout username = findViewById(R.id.username);
        String uName = String.valueOf(username.getEditText().getText());

        if(imageUri == null){
            Toast.makeText(this, "please select an image", Toast.LENGTH_SHORT).show();
        }else if (fName.isEmpty() && uName.isEmpty()){
            fullName.setError("tidak boleh kosong");
            username.setError("tidak boleh kosong");
        }else if (fName.isEmpty()){
            fullName.setError("tidak boleh kosong");
        }else if (uName.isEmpty()){
            username.setError("tidak boleh kosong");
        }else {
            Intent tes = new Intent(this, MainActivity2.class);
            tes.putExtra("fullname", fName);
            tes.putExtra("username", uName);
            tes.putExtra("profil", imageUri);
            startActivity(tes);
        }
    }
}