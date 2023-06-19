package com.example.water_quality_monitoring_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Temperature<requestQueue> extends AppCompatActivity {

    private static final String JSON_URL="https://api.thingspeak.com/channels/9/feeds.json?api_key=E52AWRAV1RSXQQJW&results=5";
    ListView listView;
    List<Hero> heroList;
    Button tback;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

//        listView =findViewById(R.id.listView);
        heroList=new ArrayList<>();

        //this method will fetch and parse data
        loadHeroList();

        tback=findViewById(R.id.tempback);
        tback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void loadHeroList(){
        final ProgressBar progressBar=(ProgressBar) findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);
        //Creating a string request to send request to the url;
        StringRequest stringRequest=new StringRequest(Request.Method.GET,JSON_URL,(response) ->{
            progressBar.setVisibility(View.INVISIBLE);

            try {
                //getting the whole json object from the responce
                JSONObject obj = new JSONObject(response);

                // we have to array named hero inside the object
                //so here we are getting that json array
                JSONArray heroArray = obj.getJSONArray("feeds");

                //now looping through all the element of the json Array
                for(int i=0;i<heroArray.length();i++){
                    //getting the json object of the particular index inside the array
                    JSONObject heroObject = heroArray.getJSONObject(i);

                    //creating a hero object and giving them the value from json object

                    Hero hero = new Hero(heroObject.getString("created_at"),heroObject.getString("field2"));
                    //adding the hero to herolist

                    heroList.add(hero);
                }

                //creating custom adaptor object
                ListViewAdapter adapter = new ListViewAdapter(heroList,getApplicationContext());

                //adding the adapter to listview
                listView.setAdapter(adapter);
            }catch (JSONException e){
                e.printStackTrace();
            }

        },(error) -> {
            Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
        });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the String request to request queue

        requestQueue.add(stringRequest);
    }
}