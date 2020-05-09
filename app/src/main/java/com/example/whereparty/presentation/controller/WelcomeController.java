package com.example.whereparty.presentation.controller;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;

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
import java.util.List;

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

        com.example.whereparty.presentation.model.concertAPI.MetroArea cache = getDataFromCache().get(0).getVenue().getMetroArea();

        assert cache != null;
        assert cache.getCountry() != null;
        if(cache.getCountry().getDisplayName()!=null && cache.getDisplayName()!=null && cache.getId()!=null) {
            List<Location> areaList = null;
            Location savedLocation = null;
            MetroArea savedMetroArea = null;
            Country savedCountry = null;

            savedCountry.setDisplayName(cache.getCountry().getDisplayName());

            savedMetroArea.setCountry(savedCountry);
            savedMetroArea.setDisplayName(cache.getDisplayName());
            savedMetroArea.setId(Integer.parseInt(cache.getId()));

            savedLocation.setMetroArea(savedMetroArea);

            areaList.add(savedLocation);

            view.showList(areaList);
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
