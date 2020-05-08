package com.example.whereparty.data;

import com.example.whereparty.presentation.model.RestConcertResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ConcertApi {
    @GET("api/3.0/metro_areas/28909/calendar.json?apikey=f7BntzROcFk22SBa")
    Call<RestConcertResponse> getConcertResponse();
}
