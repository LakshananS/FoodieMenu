package com.example.foodiemenu;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView addToCart;
    private TextView title, description, numberOfOrders, cost;
    private ImageView addBtn, removeBtn, picFood;
    private FoodDomain object;
    private int numberOfOrder = 1;
    private ManageCart manageCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        manageCart = new ManageCart(this);
        initView();
        getBundle();
    }

    private void getBundle() {
        object = (FoodDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        title.setText(object.getTitle());
        cost.setText("Rs" + object.getCost());
        description.setText(object.getDescription());
        numberOfOrders.setText(String.valueOf(numberOfOrder));

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOfOrder += 1;
                numberOfOrders.setText(String.valueOf(numberOfOrder));
            }
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOfOrder > 1) {
                    numberOfOrder -= 1;
                }
                numberOfOrders.setText(String.valueOf(numberOfOrder));
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(numberOfOrder);
                manageCart.insertFood(object);
            }
        });
    }

    private void initView() {
        addToCart = findViewById(R.id.addToCart);
        title = findViewById(R.id.titleTxt);
        cost = findViewById(R.id.cost);
        numberOfOrders = findViewById(R.id.numberOrder);
        description = findViewById(R.id.description);
        addBtn = findViewById(R.id.plus);
        removeBtn = findViewById(R.id.min);
        picFood = findViewById(R.id.pic_food);
    }
}
