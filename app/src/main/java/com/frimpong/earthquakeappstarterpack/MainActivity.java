package com.frimpong.earthquakeappstarterpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AsyncFetch.CompletionResponse {

    RecyclerView recyclerView;
    EditText searchBox;
    Button searchButton;
    List<EarthQuakeItem> earthQuakeItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // --- Setting up the list of items
        recyclerView = findViewById(R.id.listOfItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // --- Load data from website
        new AsyncFetch(this).execute();

//        EarthquakeAdapter adapter = new EarthquakeAdapter(dummy(),this);
//        recyclerView.setAdapter(adapter);

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

    @Override
    public void callback(String response) {
        // When this fires, it means we have the response(which is the XML stuff we are supposed to collect form teh website)
        // So we pass the string on to be parsed with PULL PARSER
        parseXMLFromStringAndInflate(response);

    }


    public void parseXMLFromStringAndInflate(String xmlString){
        // This is where we use pull parser to extract the items we are supposed to show
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xmlString));

            EarthQuakeItem earthquakeItem = null;

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();

                if (eventType == XmlPullParser.START_TAG) {
                    if (tagName.equalsIgnoreCase("item")) {
                        earthquakeItem = new EarthQuakeItem();
                    } else if (earthquakeItem != null) {
                        switch (tagName.toLowerCase()) {
                            case "title":
                                earthquakeItem.setName(parser.nextText());
                                break;
                            case "description":
                                earthquakeItem.setDescription(parser.nextText());
                                break;
                            case "pubdate":
                                earthquakeItem.setDate(parser.nextText());
                                break;
                        }
                    }
                } else if (eventType == XmlPullParser.END_TAG && tagName.equalsIgnoreCase("item")) {
                    earthQuakeItems.add(earthquakeItem);
                }

                eventType = parser.next();
            }

            // Inflate the recycler view here with the items
            EarthquakeAdapter adapter = new EarthquakeAdapter(earthQuakeItems,this);
            recyclerView.setAdapter(adapter);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }
}