package com.example.insta.home;

import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.insta.R;
import com.example.insta.StoryAdapter;
import com.example.insta.api.Service;
import com.example.insta.models.Photo;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    RecyclerView mainList;
    List<Photo> data;
    Retrofit retrofit = null;
    private List<Photo> mMovies = new ArrayList<>();
    Service service;
    private StoryAdapter storyAdapter;

    public HomeFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mainList = rootView.findViewById(R.id.mainListView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mainList.setLayoutManager(layoutManager);
        mainList.setHasFixedSize(true);

        try {

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(Service.class);

            //   Service request = net.accedegh.retrofitlibrary.api.Client.retrofit.create(Service.class);
            Call<List<Photo>> call = service.getPhto();
            call.enqueue(new Callback<List<Photo>>() {

                @Override
                public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                    data = response.body();
                    mainList.setAdapter(new StoryAdapter(getContext(), data));
                }

                @Override
                public void onFailure(Call<List<Photo>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                }
            });

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }

    }


}
