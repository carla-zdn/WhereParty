package com.example.whereparty.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.whereparty.Injection;
import com.example.whereparty.R;
import com.example.whereparty.presentation.controller.MainController;
import com.example.whereparty.presentation.model.Event;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(Injection.getSharedPreferences(getApplicationContext()),
                Injection.getGson(), this
        );
        controller.onStart();
    }

    public void showList(List<Event> eventList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // define an adapter
        mAdapter = new ListAdapter(eventList, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Event item) {
                controller.onRecyclerViewClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    public void showError() {
        Toast.makeText(this, "No data in cache", Toast.LENGTH_SHORT).show();
    }

    public void navigateToDetails(Event event) {

    }
}
