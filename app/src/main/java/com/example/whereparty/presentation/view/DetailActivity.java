package com.example.whereparty.presentation.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whereparty.Injection;
import com.example.whereparty.R;
import com.example.whereparty.presentation.model.concertAPI.Event;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    private WebView webView;

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        webView = findViewById(R.id.webView);

        Intent intent = getIntent();
        String eventJson = intent.getStringExtra("eventKey");
        Event detailEvent = Injection.getGson().fromJson(eventJson, Event.class);

        switch (Objects.requireNonNull(intent.getStringExtra("typeDetailKey"))){
            case "reservation":
                url = detailEvent.getUri();
                break;
            case "venueDetail":
                url = detailEvent.getVenue().getUri();
                break;
            case "artistDetail":
                url = detailEvent.getPerformance().get(0).getArtist().getUri();
                break;
        }

        showDetail();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void showDetail() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });
    }
}
