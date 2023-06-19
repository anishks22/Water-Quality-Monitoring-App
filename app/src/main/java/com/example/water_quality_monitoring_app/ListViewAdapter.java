package com.example.water_quality_monitoring_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Hero> {
//the hero list that will be displayed

    private List<Hero> heroList;

    private Context mCtx;

    public ListViewAdapter(List<Hero> heroList,Context mCtx){
        super(mCtx,R.layout.activity_temperature,heroList);
        this.heroList=heroList;
        this.mCtx=mCtx;
    }

    //this method will return the list item

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.activity_temperature,null,true);

        //getting text Views
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textViewName = listViewItem.findViewById(R.id.textViewName);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textViewImageUrl = listViewItem.findViewById(R.id.textViewImageUrl);

        //getting the hero for the specific position
        Hero hero = heroList.get(position);

        //setting hero values to textviews
        textViewName.setText(hero.getName());
        textViewImageUrl.setText(hero.getImageUrl());

        //returning the listitem
        return listViewItem;

    }
}
