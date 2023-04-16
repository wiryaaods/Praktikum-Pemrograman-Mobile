package com.example.kuis;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    CircleImageView profil;
    private static final int PICK_IMAGE=1;
    Uri profile;
    EditText name;
    TextView player1, score;
    Button apply, play;
    Player player;
    CardView layout;
    ActivityResultLauncher<Intent> resultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        player1 = findViewById(R.id.player);
        score = findViewById(R.id.score);
        apply = findViewById(R.id.apply);
        play = findViewById(R.id.play);
        layout = findViewById(R.id.layout);
        profil = findViewById(R.id.profil);
        player = new Player();

        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result -> {
            if(result.getResultCode()==RESULT_OK){
                assert result.getData() !=null;
                player = result.getData().getParcelableExtra("player");
                score.setText("best score:" + " "+ String.valueOf(player.getBestScore()));
                player.setBestScore(player.getBestScore());
                play.setText("play again!");
            }
        });

        //

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.setNama(String.valueOf(name.getText()));
                player.setScore(0);

                if(TextUtils.isEmpty(name.getText().toString())){
                    Toast.makeText(MainActivity.this, "isi nama", Toast.LENGTH_SHORT).show();
                }else{
                    name.setVisibility(View.GONE);
                    player1.setVisibility(View.VISIBLE);
                    player1.setText(player.getNama());
                    score.setText(String.valueOf("Best Score:"+ " "+player.getScore()));
                    score.setVisibility(View.VISIBLE);
                    apply.setVisibility(View.GONE);
                    play.setVisibility(View.VISIBLE);
                    layout.setVisibility(View.VISIBLE);
                }
            }
        });

        profil = (CircleImageView) findViewById(R.id.profil);
        profil.setOnClickListener(new View.OnClickListener() {
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
            profile = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), profile);
                profil.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void play(View view){
        System.out.println("best score" + player.getBestScore());
        System.out.println("score" + player.getScore());
        Intent next = new Intent (MainActivity.this, MainActivity2.class);
        next.putExtra("player", player);
        resultLauncher.launch(next);
    }
}