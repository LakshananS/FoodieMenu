package com.example.foodiemenu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.ViewHolder> {
    ArrayList<FoodList> listDomain;

    public FoodListAdapter(ArrayList<FoodList> listDomain) {
        this.listDomain = listDomain;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ListName.setText(listDomain.get(position).getTitle());
        String picUrl = "";
        switch (position) {
            case 0:
                picUrl = "a";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.brawn_button_a));
                break;
            case 1:
                picUrl = "b";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.brawn_button_b));
                break;
            case 2:
                picUrl = "c";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.brawn_button_c));
                break;
            case 3:
                picUrl = "d";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.brawn_button_d));
                break;
            case 4:
                picUrl = "e";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.brawn_button_e));
                break;
            case 5:
                picUrl = "f";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.brawn_button_f));
                break;
        }

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.ListPic);
    }

    @Override
    public int getItemCount() {
        return listDomain.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ListName;
        ImageView ListPic;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ListName = itemView.findViewById(R.id.cat_name_1);
            ListPic = itemView.findViewById(R.id.burgers);
            mainLayout = itemView.findViewById(R.id.main_layout);
        }
    }
}
