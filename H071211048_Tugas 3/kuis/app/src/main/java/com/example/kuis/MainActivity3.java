package com.example.kuis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    TextView ggwp, score, bestScore;
    Button btn;
    Player player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ggwp = findViewById(R.id.ggwp);
        score = findViewById(R.id.tv_score);
        bestScore = findViewById(R.id.tv_bestscore);
        btn = findViewById(R.id.btn);

        player = getIntent().getParcelableExtra("player");

        ggwp.setText("ggwp" +" " + player.getNama());
        score.setText(String.valueOf(player.getScore()));

        System.out.println("best score" + player.getBestScore());
        System.out.println("score" + player.getScore());

        if(player.getScore() > player.getBestScore()){
            player.setBestScore(player.getScore());
            bestScore.setText(String.valueOf(player.getScore()));
        }else{
            bestScore.setText(String.valueOf(player.getBestScore()));
        }

        System.out.println("best score after" + player.getBestScore());
        System.out.println("score after" + player.getScore());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent();
                back.putExtra("player", player);
                setResult(RESULT_OK, back);
                finish();
            }
        });
    }

}