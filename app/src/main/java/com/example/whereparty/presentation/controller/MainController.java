package com.example.whereparty.presentation.controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.whereparty.Constants;
import com.example.whereparty.Injection;
import com.example.whereparty.data.ConcertApi;
import com.example.whereparty.presentation.model.Event;
import com.example.whereparty.presentation.model.RestConcertResponse;
import com.example.whereparty.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;

    public MainController(SharedPreferences sharedPreferences, Gson gson, MainActivity view) {
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
        this.view = view;
    }

    public void onStart(){

        List<Event> eventList = getDataFromCache();

        if(eventList != null){
            view.showList(eventList);
        }else{
            makeApiCall();
        }
    }

    private List<Event> getDataFromCache() {

        String jsonEventList = sharedPreferences.getString(Constants.KEY_EVENT_LIST, null);

        if(jsonEventList == null){
            return null;
        }else{
            Type eventListType = new TypeToken<List<Event>>(){}.getType();
            return gson.fromJson(jsonEventList, eventListType);
        }

    }

    private void makeApiCall(){

        Call<RestConcertResponse> call = Injection.getConcertApi().getConcertResponse();
        call.enqueue(new Callback<RestConcertResponse>() {
            @Override
            public void onResponse(Call<RestConcertResponse> call, Response<RestConcertResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Event> eventList = response.body().getResultPage().getResults().getEvent();
                    eventList.remove(0);//Problem with the first result
                    saveList(eventList);
                    view.showList(eventList);
                }else{
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<RestConcertResponse> call, Throwable t) {
                view.showError();
            }
        });
    }

    private void saveList(List<Event> eventList) {

        String jsonEventList = gson.toJson(eventList);

        sharedPreferences
                .edit()
                .putInt("cle_integer", 3)
                .putString(Constants.KEY_EVENT_LIST, jsonEventList)
                .apply();
    }

    public void onRecyclerViewClick(Event event, String typeDetail) {
        view.navigateToDetails(event,typeDetail);
    }
}
