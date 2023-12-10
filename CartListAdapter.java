package com.example.foodiemenu;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private ArrayList<FoodDomain> foodDomain;
    private ManageCart manageCart;
    private ChangeNumberItemListener changeNumberItemListener;

    public CartListAdapter(ArrayList<FoodDomain> foodDomain, ManageCart manageCart, ChangeNumberItemListener changeNumberItemListener) {
        this.foodDomain = foodDomain;
        this.manageCart = manageCart;
        this.changeNumberItemListener = changeNumberItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        FoodDomain item = foodDomain.get(position);
        holder.title.setText(item.getTitle());
        holder.costEachItem.setText("Rs" + item.getCost());
        holder.totalEachItem.setText("Total: Rs" + (item.getNumberInCart() * item.getCost()));
        holder.num.setText(String.valueOf(item.getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(item.getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeNumberItemListener.onIncreaseClicked(position);
            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeNumberItemListener.onDecreaseClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodDomain.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, costEachItem, totalEachItem, num;
        ImageView pic, add, remove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.food_name);
            costEachItem = itemView.findViewById(R.id.feeEachItem);
            totalEachItem = itemView.findViewById(R.id.total_amount);
            num = itemView.findViewById(R.id.item_value);
            pic = itemView.findViewById(R.id.imageView9);
            add = itemView.findViewById(R.id.add);
            remove = itemView.findViewById(R.id.remove);
        }
    }

    public interface ChangeNumberItemListener {
        void onIncreaseClicked(int position);
        void onDecreaseClicked(int position);
    }
}
