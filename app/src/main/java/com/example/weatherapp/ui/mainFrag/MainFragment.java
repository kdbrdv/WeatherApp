package com.example.weatherapp.ui.mainFrag;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.example.weatherapp.R;
import com.example.weatherapp.base.BaseFragment;
import com.example.weatherapp.common.Resource;
import com.example.weatherapp.data.local.WeatherDao;
import com.example.weatherapp.data.models.Main;
import com.example.weatherapp.data.models.Sys;
import com.example.weatherapp.data.models.Weather;
import com.example.weatherapp.data.models.Weather__1;
import com.example.weatherapp.data.models.Wind;
import com.example.weatherapp.databinding.FragmentMainBinding;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainFragment extends BaseFragment<FragmentMainBinding> {

    private NavController navController;
    private MainViewModel viewModel;
    private Wind wind;
    private Main main;
    private MainFragmentArgs args;
    private Weather weather;
    private Sys sys;
    private ArrayList<Weather__1> weather__1 = new ArrayList<>();
    @Inject
    WeatherDao dao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().
                getSupportFragmentManager().findFragmentById(R.id.nav_host);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();
        if (getArguments() !=null){
            args = MainFragmentArgs.fromBundle(getArguments());
        }
    }

    @Override
    protected FragmentMainBinding bind() {
        return FragmentMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setupObservers() {
        viewModel.tempData.observe(getViewLifecycleOwner(), responce -> {
            switch (responce.status){
                case SUCCESS:
                    wind = responce.data.getWind();
                    weather = responce.data;
                    main = responce.data.getMain();
                    sys = responce.data.getSys();
                    weather__1 = (ArrayList<Weather__1>) responce.data.getWeather();
                    binding.progress.setVisibility(View.GONE);
                    setWeather();
                    break;
                case LOADING:
                    binding.progress.setVisibility(View.VISIBLE);
                    break;
                case ERROR:
                    Toast.makeText(requireActivity(), "Internet not connected", Toast.LENGTH_SHORT).show();
                    wind = dao.getWeather().getWind();
                    weather__1 = (ArrayList<Weather__1>) dao.getWeather().getWeather();
                    main = dao.getWeather().getMain();
                    sys = dao.getWeather().getSys();
                    weather = dao.getWeather();
                    binding.progress.setVisibility(View.GONE);
                    break;
            }

        });
    }

    private void setWeather() {
        binding.weatherNowTv.setText(weather__1.get(0).getMain());
        Glide.with(requireContext()) .load("https://openweathermap.org/img/wn/" + weather__1.get(0).getIcon() + ".png")
                .override(100, 100) .into(binding.weatherIv);
        binding.tempmaxTv.setText(String.valueOf((int) Math.round(main.getTempMax())));
        binding.windTv.setText((int) Math.round(wind.getSpeed()) + " m/ s");
        binding.cityBtn.setText(weather.getName()); binding.tempnowTv.setText(String.valueOf((int) Math.round(main.getTemp())));
        binding.barometrTv.setText(main.getPressure() + "mBar"); binding.textViewHumidity.setText(main.getHumidity() + "%");
        binding.tempminTv.setText(String.valueOf((int) Math.round(main.getTempMin())));
    }

    @Override
    protected void setupUI() {
        binding.cityBtn.setOnClickListener(v -> {
            navController.navigate(R.id.action_mainFragment_to_mapFragment);
        });
            viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
            viewModel.fetchTemp(args.getLatitude(),args.getLongitude());
    }
}