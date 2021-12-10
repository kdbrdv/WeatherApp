package com.example.weatherapp.data.local.converter;

import androidx.room.TypeConverter;

import com.example.weatherapp.data.models.Main;
import com.example.weatherapp.data.models.Weather__1;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Weather1Converter {

    @TypeConverter
    public String fromMainString(List<Weather__1> main){
        if (main == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Weather__1>>(){}.getType();
        return gson.toJson(main,type);
    }
    @TypeConverter
    public List<Weather__1> fromMainString(String fromString){
        if (fromString == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Weather__1>>(){}.getType();
        return gson.fromJson(fromString,type);
    }
}
