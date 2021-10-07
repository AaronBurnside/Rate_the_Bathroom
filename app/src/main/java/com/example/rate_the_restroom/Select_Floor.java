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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_floor);

        ;
        //ImageButton button = (ImageButton) findViewById(R.id.F0male);

    }
    public void Open_F0_M_Bathroom(View view) {
        Intent intent = new Intent(this, STC_F0_comment_feed.class);
        startActivity(intent);
    }
    public void Open_F0_F_Bathroom(View view) {
        Intent intent = new Intent(this, STC_F0_comment_feed.class); //TODO seperate male and female bathrooms into seperate activities
        startActivity(intent);
    }
    public void Open_F1_M_Bathroom(View view) {
        Intent intent = new Intent(this, STC_F1_comment_feed.class);
        startActivity(intent);
    }
    public void Open_F1_F_Bathroom(View view) {
        Intent intent = new Intent(this, STC_F1_comment_feed.class);
        startActivity(intent);
    }

}