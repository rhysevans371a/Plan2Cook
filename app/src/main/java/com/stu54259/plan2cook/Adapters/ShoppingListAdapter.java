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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.stu54259.plan2cook.Model.Shopping_List;
import com.stu54259.plan2cook.R;

import java.util.List;

/**
 * All this code is created by Rhys Evans, STU54259.
 */
public class ShoppingListAdapter extends RecyclerView.Adapter<com.stu54259.plan2cook.Adapters.ShoppingListAdapter.ViewHolder> {

    private List<Shopping_List> shopList;
    private LayoutInflater mInflater;
    private com.stu54259.plan2cook.Adapters.RecyclerViewAdapter.ItemClickListener mClickListener;

    // data is passed into the constructor
    public ShoppingListAdapter(Context context, List<Shopping_List> data) {
        this.mInflater = LayoutInflater.from(context);
        this.shopList = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public com.stu54259.plan2cook.Adapters.ShoppingListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fragment_item1, parent, false);
        return new com.stu54259.plan2cook.Adapters.ShoppingListAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(com.stu54259.plan2cook.Adapters.ShoppingListAdapter.ViewHolder holder, int position) {
        if (shopList.get(position) != null) {
            holder.myTextView.setText(shopList.get(position).toString());
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return shopList.size();
    }

    // allows clicks events to be caught
    void setClickListener(com.stu54259.plan2cook.Adapters.RecyclerViewAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.quantity);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}

