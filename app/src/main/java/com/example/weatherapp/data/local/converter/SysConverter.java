package com.example.weatherapp.data.local.converter;

import androidx.room.TypeConverter;

import com.example.weatherapp.data.models.Main;
import com.example.weatherapp.data.models.Sys;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class SysConverter {

    @TypeConverter
    public String fromMainString(Sys main){
        if (main == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Sys>(){}.getType();
        return gson.toJson(main,type);
    }
    @TypeConverter
    public Sys fromMainString(String fromString){
        if (fromString == null){
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Sys>(){}.getType();
        return gson.fromJson(fromString,type);
    }
}
