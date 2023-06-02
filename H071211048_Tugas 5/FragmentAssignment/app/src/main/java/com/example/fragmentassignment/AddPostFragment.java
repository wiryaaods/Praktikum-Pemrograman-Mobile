package com.example.fragmentassignment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddPostFragment extends Fragment {

    private ImageView post;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    TextView caption;
    Button upload;
    static ArrayList<Post> userInputList= new ArrayList<>();
    ActivityResultLauncher<Intent> takeImage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_post, container, false);
        getActivity().setTitle("upload");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        takeImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if(result.getResultCode() == RESULT_OK && result.getData() !=null){
                imageUri = result.getData().getData();
                if(imageUri !=null){
                    post.setImageURI(imageUri);
                }
            }
        });

        caption = view.findViewById(R.id.caption);
        post = view.findViewById(R.id.post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                takeImage.launch(gallery);
            }
        });

        upload = view.findViewById(R.id.upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String capt = caption.getText().toString();
                if (imageUri == null) {
                    Toast.makeText(getActivity(), "please pick a photo first", Toast.LENGTH_SHORT).show();
                } else {
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("post", userInputList);

                        Post data = new Post(capt, imageUri);
                        userInputList.add(data);

                        HomeFragment homeFragment = new HomeFragment();
                        homeFragment.setArguments(bundle);

                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, homeFragment).commit();
                        Toast.makeText(getActivity(), "post success", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

