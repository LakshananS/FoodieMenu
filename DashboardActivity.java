package com.example.foodiemenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DashboardActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        imageView = findViewById(R.id.imageView2);
        textView = findViewById(R.id.textView9);
        String userName = getIntent().getStringExtra("userName");
        String profilePic = getIntent().getStringExtra("profilePic");
        textView.setText(userName);
        Picasso.get().load(profilePic).into(imageView);

        ImageView menuView = findViewById(R.id.menuView);
        menuView.setOnClickListener(new View.OnClickListener() { // <-- Fix typo here
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, FoodListActivity2.class);
                startActivity(intent);
                finish();
            }
        });
    }
}