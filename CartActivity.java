package com.example.foodiemenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {
    private Button finalised;
    private ImageView homeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        finalised = findViewById(R.id.button3);
        finalised.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CartActivity.this, PayActivity.class);
                startActivity(intent); // add this line
            }
        });
        ImageView homeImageView = findViewById(R.id.imageView8);
        homeImageView.setOnClickListener(new View.OnClickListener() { // <-- Fix typo here
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, FoodListActivity2.class);
                startActivity(intent);
            }
        });
    }
}