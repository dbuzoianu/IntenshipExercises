package com.example.intenshipexercises.server;

import com.example.intenshipexercises.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PostService {
    @GET("/posts")
    Call<List<Post>> getPosts();
}
