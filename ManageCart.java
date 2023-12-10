package com.example.foodiemenu;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class  ManageCart {
    private Context context;
    private DatabaseReference cartRef;

    public ManageCart(Context context) {
        this.context = context;
        this.cartRef = FirebaseDatabase.getInstance().getReference().child("Cart");
    }

    public void insertFood(FoodDomain item) {
        String cartItemId = cartRef.push().getKey();
        cartRef.child(cartItemId).setValue(item)
                .addOnSuccessListener(aVoid -> Toast.makeText(context, "Added to Your Cart", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(context, "Failed to add to Cart", Toast.LENGTH_SHORT).show());
    }

    public void plusNumberFood(ArrayList<FoodDomain> listFood, int position, ChangeNumerItemListner changeNumerItemListner){
        listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart()+1);
        changeNumerItemListner.changed();
    }

    public void minusNumberFood(ArrayList<FoodDomain> listFood, int position, ChangeNumerItemListner changeNumerItemListner){
        if(listFood.get(position).getNumberInCart()==1){
            listFood.remove(position);
        } else {
            listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart()-1);
        }
        changeNumerItemListner.changed();
    }
    public Double getTotalCost(){
        ArrayList<FoodDomain> listFood = getListCart();
        double fee =0;
        for (int i=0; i<listFood.size();i++){
            fee = fee + (listFood.get(i).getCost()*listFood.get(i).getNumberInCart());
        }
        return fee;
    }

    private ArrayList<FoodDomain> getListCart() {
        ArrayList<FoodDomain> list = null;
        return list;
    }
}
