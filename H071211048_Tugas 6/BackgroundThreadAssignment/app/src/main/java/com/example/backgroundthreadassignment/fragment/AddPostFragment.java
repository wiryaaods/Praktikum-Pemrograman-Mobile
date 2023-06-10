package com.example.backgroundthreadassignment.fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.backgroundthreadassignment.datasource.DataPost;
import com.example.backgroundthreadassignment.model.Post;
import com.example.backgroundthreadassignment.R;

import java.util.ArrayList;

public class AddPostFragment extends Fragment {

    private ImageView post;
    private static final int PICK_IMAGE = 1;
    String imageUri;
    TextView caption;
    Button upload;
    private ArrayList<DataPost> postList= new ArrayList<>();
    ActivityResultLauncher<Intent> takeImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_post, container, false);
        getActivity().setTitle("inigaram");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        takeImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent resultIntent = result.getData();

                if (resultIntent != null) {
                    imageUri = String.valueOf(resultIntent.getData());
                    post.setImageURI(Uri.parse(imageUri));
                }
            }
        });

        caption = view.findViewById(R.id.caption);
        post = view.findViewById(R.id.post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                takeImage.launch(Intent.createChooser(gallery, "Choose Photo!"));
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
                    DataPost.postList.add(new Post("wiiryaods", "Wiryaods", capt, "https://i.pinimg.com/236x/92/4c/f5/924cf5ecf1a4c2abde419738dcead483.jpg", imageUri));
                    imageUri = null;
                    HomeFragment homeFragment = new HomeFragment();
                    getParentFragmentManager().beginTransaction().replace(R.id.frame_container, homeFragment).commit();
                    Toast.makeText(getActivity(), "Post Success", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}