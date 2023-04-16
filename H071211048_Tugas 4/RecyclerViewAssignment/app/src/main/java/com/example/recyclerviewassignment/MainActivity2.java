package com.example.recyclerviewassignment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.stream.Collectors;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity2 extends AppCompatActivity {

    ImageView back;
    CircleImageView img;
    LinearLayout chat;
    TextView nama;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        back =findViewById(R.id.back);
        img = findViewById(R.id.profil);
        nama = findViewById(R.id.nama);
        chat = findViewById(R.id.chat);

        RecyclerView rv = findViewById(R.id.rChat);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rChatAdapter adapter = new rChatAdapter(DataSource.chats);
        rv.setAdapter(adapter);

        Chat chat1 = getIntent().getParcelableExtra("nama");
        chat.setOnClickListener(view -> {
            Intent sent = new Intent(MainActivity2.this, Profile.class);
            sent.putExtra("profile", chat1);
            startActivity(sent);

        });
        nama.setText(chat1.getNama());
        img.setImageResource(chat1.getFoto());

        setUpAdapter(chat1.getPesan());
    }

    private void setUpAdapter(String lastChat){
        RecyclerView rv = findViewById(R.id.rChat);
        rv.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Chat> chats = DataSource.generateDummychat();
        Chat selectedChat = chats.stream().filter(chat -> chat.getPesan().equals(lastChat)).collect(Collectors.toList()).get(0);
        chats.remove(selectedChat);
        chats.add(selectedChat);
        rChatAdapter roomChatAdapter = new rChatAdapter(chats);
        rv.setAdapter(roomChatAdapter);
    }


    public void back(View view) {
        finish();
    }

}
