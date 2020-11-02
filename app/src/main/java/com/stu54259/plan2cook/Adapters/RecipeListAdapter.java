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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stu54259.plan2cook.Model.Category;
import com.stu54259.plan2cook.R;

import java.util.List;

/**
 * All this code is created by Rhys Evans, STU54259.
 */
public class RecipeListAdapter extends RecyclerView.Adapter<com.stu54259.plan2cook.Adapters.RecipeListAdapter.ViewHolder> {

    private List<Category> listRecipe;
    private LayoutInflater mInflater;
    private OnRecipeClickListener clickListener;

    // data is passed into the constructor
    public RecipeListAdapter(Context context, List<Category> data, OnRecipeClickListener clickListener) {
        this.mInflater = LayoutInflater.from(context);
        this.listRecipe = data;
        this.clickListener = clickListener;
    }

    // inflates the row layout from xml when needed
    @Override
    public com.stu54259.plan2cook.Adapters.RecipeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cardview_recipe, parent, false);
        return new com.stu54259.plan2cook.Adapters.RecipeListAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull com.stu54259.plan2cook.Adapters.RecipeListAdapter.ViewHolder holder, int position) {
        holder.myTextView1.setText(listRecipe.get(position).getRecipe_name());
        holder.myTextView2.setText(listRecipe.get(position).getCategory_name());
        String image2 = listRecipe.get(position).getImage2();
        Bitmap myBitmap = BitmapFactory.decodeFile(image2);
        if (myBitmap != null)
            holder.imgImage.setImageBitmap(myBitmap);
        else
            holder.imgImage.setImageResource(listRecipe.get(position).getImage());

        holder.imgImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onRecipeClicked(position,
                        holder.myTextView1.getText().toString());
            }
        });
    }


    // total number of rows
    @Override
    public int getItemCount() {
        return listRecipe.size();
    }


    // parent activity will implement this method to respond to click events
    public interface OnRecipeClickListener {
        void onRecipeClicked(int position, String recipe_name);
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView1;
        TextView myTextView2;
        ImageView imgImage;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView1 = itemView.findViewById(R.id.txtDay);
            myTextView2 = itemView.findViewById(R.id.dayOfWeek);
            imgImage = itemView.findViewById(R.id.imgImage);
            myTextView1.setOnClickListener(this);

        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {

        }
    }
}

