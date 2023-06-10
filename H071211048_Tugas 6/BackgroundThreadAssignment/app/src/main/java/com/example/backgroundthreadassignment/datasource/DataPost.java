package com.example.backgroundthreadassignment.datasource;

import com.example.backgroundthreadassignment.R;
import com.example.backgroundthreadassignment.model.Post;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DataPost {
    public static ArrayList<Post> postList = getPosts();

    public static ArrayList<Post> getPosts(){
        ArrayList<Post> postList = new ArrayList<>();

        postList.add(new Post("hoshi", "Kwon Soonyoung", "5/13", "https://i.pinimg.com/236x/42/ba/5d/42ba5daca3392c499df644edd6546dac.jpg", "https://i.pinimg.com/236x/42/ba/5d/42ba5daca3392c499df644edd6546dac.jpg"));
        postList.add(new Post("jun", "Moon Junhui", "4/13", "https://i.pinimg.com/736x/bd/d3/f0/bdd3f00a618c96b7b8cc303f8ebed788.jpg", "https://i.pinimg.com/736x/bd/d3/f0/bdd3f00a618c96b7b8cc303f8ebed788.jpg"));
        postList.add(new Post("joshua", "Hong Jisoo", "3/13", "https://i.pinimg.com/736x/81/fc/82/81fc827c579e421b229554899f8156c7.jpg", "https://i.pinimg.com/736x/81/fc/82/81fc827c579e421b229554899f8156c7.jpg"));
        postList.add(new Post("jeonghan", "Yoon Jeonghan", "2/13", "https://i.pinimg.com/736x/e5/74/3a/e5743a62a05c0156d61610204f02b55c.jpg", "https://i.pinimg.com/736x/e5/74/3a/e5743a62a05c0156d61610204f02b55c.jpg"));
        postList.add(new Post("scoups", "Choi Seungcheol", "1/13", "https://i.pinimg.com/564x/88/f6/0a/88f60a93adcae43763ca68f6fd618911.jpg", "https://i.pinimg.com/564x/88/f6/0a/88f60a93adcae43763ca68f6fd618911.jpg"));

        return postList;
    }

    public static ArrayList<Post> searchPostModels(String query) {
        ArrayList<Post> searchedPost = new ArrayList<>();

        for (int i = 0; i < postList.size(); i++) {
            final Post postModel = postList.get(i);
            String q = query.toLowerCase();
            String name = postModel.getFullName().toLowerCase();
            String username = postModel.getUserName().toLowerCase();
            if (name.startsWith(query) || username.startsWith(query)) {
                searchedPost.add(postModel);
            }
        }
        return searchedPost;
    }
}
