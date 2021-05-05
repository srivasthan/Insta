package com.example.insta.api;

import com.example.insta.models.Photo;
import com.example.insta.models.Story;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import static android.R.id.list;


public interface Service {
    @GET("/posts")
    Call<List<Photo>> getPhto();
}


