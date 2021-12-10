package com.example.weatherapp.ui.mainFrag;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.common.Resource;
import com.example.weatherapp.data.models.Weather;
import com.example.weatherapp.data.repository.MainRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends ViewModel {

    private MainRepository repository;
    public LiveData<Resource<Weather>> tempData;

    @Inject
    public MainViewModel(MainRepository repository) {
        this.repository = repository;
    }
    public void fetchTemp(String lat,String lon){
        tempData = repository.getTemp(lat,lon);
    }
}
