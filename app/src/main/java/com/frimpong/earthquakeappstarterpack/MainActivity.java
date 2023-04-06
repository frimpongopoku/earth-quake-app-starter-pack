package com.frimpong.earthquakeappstarterpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText searchBox;
    Button searchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // --- Setting up the list of items
        recyclerView = findViewById(R.id.listOfItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        EarthquakeAdapter adapter = new EarthquakeAdapter(dummy(),this);
        recyclerView.setAdapter(adapter);

    }

    public List<EarthQuakeItem> dummy(){
        ArrayList<EarthQuakeItem> earthquakeItems = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            EarthQuakeItem earthquakeItem = new EarthQuakeItem();
            earthquakeItem.setName("Earthquake " + (i+1));
            earthquakeItem.setDescription("This is a description for earthquake " + (i+1));
            earthquakeItem.setDate("2022-03-25");
            earthquakeItems.add(earthquakeItem);
        }

        return earthquakeItems;
    }


    public  void doSearch(View v){
        // TODO: what should happen when the "search" button is clicked
    }
}