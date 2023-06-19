package com.example.water_quality_monitoring_app;

public class Hero {
    //name = entry_id ,imageUrl= field1

    String entry_id,field2;

    public Hero(String entry_id,String field2){
        this.entry_id=entry_id;
        this.field2=field2;
    }

    public String getName(){
        return entry_id;
    }

    public String getImageUrl(){
        return field2;
    }
}
