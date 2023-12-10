package com.example.foodiemenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class FoodListActivity2 extends AppCompatActivity {
    private FoodListAdapter adapter;
    private RecyclerView recyclerViewList;
    private FirebaseAuth auth;
    private Button logout,bill;
    private ImageView cart,dashB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list2);
        cart = findViewById(R.id.cart);
        cart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(FoodListActivity2.this,CartActivity.class);
                startActivity(intent);
            }
        });
        dashB = findViewById(R.id.dashB);
        dashB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(FoodListActivity2.this, DashboardActivity2.class);
                startActivity(intent);
            }
        });
        auth = FirebaseAuth.getInstance();
        logout = findViewById(R.id.logout);
        FirebaseUser user = auth.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(FoodListActivity2.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            logout.setText(user.getEmail());
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(FoodListActivity2.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerViewList = findViewById(R.id.recyclerView);
        setupRecyclerView();
        populateFoodList();
    }

    private void populateFoodList() {
        List<FoodList> foodList = new ArrayList<>();
        foodList.add(new FoodList("Appetizers", "a"));
        foodList.add(new FoodList("Burgers", "b"));
        foodList.add(new FoodList("Chicken", "c"));
        foodList.add(new FoodList("Submarine", "d"));
        foodList.add(new FoodList("Beverages", "e"));
        foodList.add(new FoodList("Add Ons", "f"));

        adapter = new FoodListAdapter((ArrayList<FoodList>) foodList);
        recyclerViewList.setAdapter(adapter);
    }


    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
    }
}
