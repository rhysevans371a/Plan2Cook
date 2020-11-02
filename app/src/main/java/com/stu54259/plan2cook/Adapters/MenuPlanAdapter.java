/*
 * ***************************************************************************************************************
 * Plan2Cook
 * Copyright (c) 2020
 *  Rhys Evans
 * STU54259 - Arden University
 * ***************************************************************************************************************
 */

package com.stu54259.plan2cook.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stu54259.plan2cook.Model.Plan_Recipes;
import com.stu54259.plan2cook.R;
import com.stu54259.plan2cook.Recipe;

import java.util.List;

/**
 * All this code is created by Rhys Evans, STU54259.
 */
public class MenuPlanAdapter extends RecyclerView.Adapter<MenuPlanAdapter.ViewHolder> {

    private List<Plan_Recipes> menuPlan;
    private LayoutInflater mInflater;
    private MenuPlanAdapter.ItemClickListener mClickListener;

    // data is passed into the constructor
    public MenuPlanAdapter(Context context, List<Plan_Recipes> data) {
        this.mInflater = LayoutInflater.from(context);
        this.menuPlan = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.menu_plan_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.myTextView1.setText(menuPlan.get(position).getDate().toString());
        holder.myTextView2.setText(menuPlan.get(position).getDayOfWeek());
        holder.myTextView3.setText(menuPlan.get(position).getRecipe_name());
        holder.myTextView4.setText(menuPlan.get(position).getDate_string());
        String image2 = menuPlan.get(position).getImage2();
        Bitmap myBitmap = BitmapFactory.decodeFile(image2);
        if (myBitmap != null)
            holder.imgImage.setImageBitmap(myBitmap);
        else
            holder.imgImage.setImageResource(menuPlan.get(position).getImage());
    }


    // total number of rows
    @Override
    public int getItemCount() {
        return menuPlan.size();
    }

    // allows clicks events to be caught
    void setClickListener(MenuPlanAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);

    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView1;
        TextView myTextView2;
        TextView myTextView3;
        TextView myTextView4;
        ImageView imgImage;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView1 = itemView.findViewById(R.id.txtDay);
            myTextView2 = itemView.findViewById(R.id.dayOfWeek);
            myTextView3 = itemView.findViewById(R.id.recipeName);
            myTextView4 = itemView.findViewById(R.id.dateString);
            imgImage = itemView.findViewById(R.id.imgImage);
            itemView.setOnClickListener(this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Recipe.class);
                    intent.putExtra("NAME", myTextView1.getText().toString());
                    view.getContext().startActivity(intent);
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
}


