package com.example.whereparty.presentation.controller;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.whereparty.Constants;
import com.example.whereparty.Injection;
import com.example.whereparty.R;
import com.example.whereparty.presentation.model.areaAPI.Country;
import com.example.whereparty.presentation.model.areaAPI.Location;
import com.example.whereparty.presentation.model.areaAPI.MetroArea;
import com.example.whereparty.presentation.model.areaAPI.RestAreaResponse;
import com.example.whereparty.presentation.model.concertAPI.Event;
import com.example.whereparty.presentation.model.concertAPI.RestConcertResponse;
import com.example.whereparty.presentation.view.WelcomeActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WelcomeController {

    private WelcomeActivity view;
    private Button searchButton;
    private EditText editText;
    private ImageView locationImageView;

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public WelcomeController(WelcomeActivity view, SharedPreferences sharedPreferences, Gson gson) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }

    public void onStart(){

        editText = view.findViewById(R.id.editText);
        locationImageView = view.findViewById(R.id.imageView);
        locationImageView.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeApiCall("localisation");
            }
        }));

        searchButton = view.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeApiCall(editText.getText().toString());
            }
        });

        /*com.example.whereparty.presentation.model.concertAPI.MetroArea*/ List<Event> getCache = getDataFromCache();//.get(0).getVenue().getMetroArea();


        if(getCache != null) {

            com.example.whereparty.presentation.model.concertAPI.MetroArea cache = getCache.get(0).getVenue().getMetroArea();

            if (cache.getCountry().getDisplayName() != null && cache.getDisplayName() != null && cache.getId() != null) {
                Country savedCountry = new Country(cache.getCountry().getDisplayName());
                MetroArea savedMetroArea = new MetroArea(savedCountry, cache.getDisplayName(), Integer.parseInt(cache.getId()));
                Location savedLocation = new Location(savedMetroArea);
                List<Location> areaList = new ArrayList<>();

                areaList.add(savedLocation);

                view.showList(areaList);
            }
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

    private void makeApiCall(String query){

        Call<RestAreaResponse> call;

        if(query.equals("localisation")){
            call = Injection.getConcertApi().getAreaResponse("https://api.songkick.com/api/3.0/search/locations.json?location=clientip&apikey=" + Constants.API_KEY);
        }else{
            call = Injection.getConcertApi().getAreaResponse("https://api.songkick.com/api/3.0/search/locations.json?query=" + query + "&apikey=" + Constants.API_KEY);
        }
        call.enqueue(new Callback<RestAreaResponse>() {
            @Override
            public void onResponse(@NonNull Call<RestAreaResponse> call, @NonNull Response<RestAreaResponse> response) {
                assert response.body() != null;
                if(response.isSuccessful() && response.body().getResultsPage().getResults() != null){
                    List<Location> areaList = response.body().getResultsPage().getResults().getLocation();
                    view.showList(areaList);
                }else{
                    view.showError();
                }
            }

            @Override
            public void onFailure(@NonNull Call<RestAreaResponse> call, @NonNull Throwable t) {
                view.showError();
            }
        });
    }

    public void onRecyclerViewClick(String id) {
        view.navigateToDetails(id);
    }

}
