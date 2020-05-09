package com.example.whereparty.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whereparty.Injection;
import com.example.whereparty.R;
import com.example.whereparty.presentation.controller.MainController;
import com.example.whereparty.presentation.controller.WelcomeController;
import com.example.whereparty.presentation.model.areaAPI.Location;
import com.example.whereparty.presentation.model.concertAPI.Event;

import java.util.List;

public class WelcomeActivity extends AppCompatActivity {


    private WelcomeController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        controller = new WelcomeController(this, Injection.getSharedPreferences(getApplicationContext()),
                Injection.getGson());
        controller.onStart();
    }

    public void showList(List<Location> locationList) {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewWelcome);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // define an adapter
        WelcomeListAdapter mAdapter = new WelcomeListAdapter(locationList, new WelcomeListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String id) {
                controller.onRecyclerViewClick(id);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    public void showError() {
        Toast.makeText(this, "La recherche n'a pas pu aboutir", Toast.LENGTH_SHORT).show();
    }

    public void navigateToDetails(String id) {
        Intent myIntent = new Intent(WelcomeActivity.this, MainActivity.class);

        myIntent.putExtra("idKey", id);
        WelcomeActivity.this.startActivity(myIntent);
    }
}
