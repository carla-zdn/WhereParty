package com.example.whereparty.data;

import com.example.whereparty.presentation.model.areaAPI.RestAreaResponse;
import com.example.whereparty.presentation.model.concertAPI.RestConcertResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ConcertApi {
    @GET()
    Call<RestConcertResponse> getConcertResponse(@Url String url);

    @GET
    Call<RestAreaResponse> getAreaResponse(@Url String url);
}
