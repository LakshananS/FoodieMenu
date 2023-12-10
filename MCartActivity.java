package com.example.foodiemenu;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MCartActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private ManageCart manageCart;
    private TextView totalCost, total;
    private ScrollView scrollView;
    private Button Checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcart);

        manageCart = new ManageCart(this);

        intitView();
    }

    private void intitView() {
        recyclerView = findViewById(R.id.rcView);
        total = findViewById(R.id.total);
        Checkout = findViewById(R.id.checkOut);
        totalCost = findViewById(R.id.total_cost);
        scrollView = findViewById(R.id.scroll);
    }

    private void iniitList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}