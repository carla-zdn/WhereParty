package com.example.whereparty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    static final String BASE_URL = "https://api.songkick.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("cache_concert", Context.MODE_PRIVATE);

        gson = new GsonBuilder()
                .setLenient()
                .create();

        makeApiCall();
    }

    private void showList(List<Event> eventList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // define an adapter
        mAdapter = new ListAdapter(eventList);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ConcertApi concertApi = retrofit.create(ConcertApi.class);

        Call<RestConcertResponse> call = concertApi.getConcertResponse();
        call.enqueue(new Callback<RestConcertResponse>() {
            @Override
            public void onResponse(Call<RestConcertResponse> call, Response<RestConcertResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Event> eventList = response.body().getResultPage().getResults().getEvent();
                    saveList(eventList);
                    showList(eventList);
                }else{
                    showError();
                }
            }

            @Override
            public void onFailure(Call<RestConcertResponse> call, Throwable t) {
                showError();
            }
        });
    }

    private void saveList(List<Event> eventList) {

        String jsonEventList = gson.toJson(eventList);

        sharedPreferences
                .edit()
                .putInt("cle_integer", 3)
                .putString("jsonEventList", jsonEventList)
                .apply();
    }

    private void showError() {
        Toast.makeText(this, "API Error", Toast.LENGTH_SHORT).show();
    }
}
