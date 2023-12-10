package com.example.foodiemenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView mapImageView = findViewById(R.id.Map);
        mapImageView.setOnClickListener(new View.OnClickListener() { // <-- Fix typo here
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ImageView menuImage = findViewById(R.id.Move);
        menuImage.setOnClickListener(new View.OnClickListener() { // <-- Fix typo here
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FoodListActivity2.class);
                startActivity(intent);
                finish();
            }
        });
    }
}