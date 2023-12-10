package com.example.foodiemenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class PayActivity extends AppCompatActivity {
    private Button finalise;
    private ImageView moveto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        finalise = findViewById(R.id.finalise);
        finalise.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(PayActivity.this, FoodListActivity2.class);
                startActivity(intent);
            }
        });

        moveto = findViewById(R.id.moveto);
        moveto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(PayActivity.this,FinalActivity.class);
                startActivity(intent);
            }
        });
    }
}