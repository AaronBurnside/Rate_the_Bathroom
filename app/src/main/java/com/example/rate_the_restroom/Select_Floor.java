package com.example.rate_the_restroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.app.ProgressDialog;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.rate_the_restroom.databinding.ActivitySelectFloorBinding;

public class Select_Floor extends AppCompatActivity {

    private ActivitySelectFloorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySelectFloorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_select_floor);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        ImageButton button = (ImageButton) findViewById(R.id.F0male);

    }
    public void Open_F0_M_Bathroom(View view) {
        Intent intent = new Intent(this, MapsActivity.class);;
        startActivity(intent);
    }
    public void Open_F0_F_Bathroom(View view) {
        Intent intent = new Intent(this, MapsActivity.class);;
        startActivity(intent);
    }
    public void Open_F1_M_Bathroom(View view) {
        Intent intent = new Intent(this, MapsActivity.class);;
        startActivity(intent);
    }
    public void Open_F1_F_Bathroom(View view) {
        Intent intent = new Intent(this, MapsActivity.class);;
        startActivity(intent);
    }

}