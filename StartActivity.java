package com.example.foodiemenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
    private Button moveto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
            setContentView(R.layout.activity_start);
            moveto = findViewById(R.id.Next);
            moveto.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(StartActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            });

        }
    }
