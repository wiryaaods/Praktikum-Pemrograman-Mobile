package com.example.backgroundthreadassignment.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.backgroundthreadassignment.datasource.DataPost;
import com.example.backgroundthreadassignment.model.Post;
import com.example.backgroundthreadassignment.R;
import com.example.backgroundthreadassignment.adapter.SearchAdapter;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SearchFragment extends Fragment {
    private EditText et;
    private ProgressBar progressBar;
    private ImageView cancelSearch;
    private SearchAdapter searchAdapter;
    private RecyclerView recyclerView;

    public SearchFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        getActivity().setTitle("search");
        return view;
    }

    private ArrayList<Post> posts = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       et = view.findViewById(R.id.et);
       cancelSearch = view.findViewById(R.id.iv_cancelSearch);
       progressBar = view.findViewById(R.id.progressBar);
       searchAdapter = new SearchAdapter();

        recyclerView = view.findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(searchAdapter);

        et.setHorizontallyScrolling(true);

        progressBar.setVisibility(View.GONE);
        cancelSearch.setVisibility(View.GONE);

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchUser(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                cancelSearch.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        cancelSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et.setText("");
            }
        });
    }

    private void searchUser(CharSequence charSequence){
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        Executor executor = Executors.newSingleThreadExecutor();

        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            handler.post(() -> {
                if (TextUtils.isEmpty(charSequence)) {
                    searchAdapter.setPostList(new ArrayList<>());
                } else {
                    searchAdapter.setPostList(DataPost.searchPostModels(charSequence.toString()));
                }
                searchAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            });
        });
    }

}