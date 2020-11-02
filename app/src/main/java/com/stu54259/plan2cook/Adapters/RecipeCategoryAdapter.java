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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stu54259.plan2cook.Model.Category;
import com.stu54259.plan2cook.R;
import com.stu54259.plan2cook.Recipe;

import java.util.List;

/**
 * All this code is created by Rhys Evans, STU54259.
 */
public class RecipeCategoryAdapter extends RecyclerView.Adapter<RecipeCategoryAdapter.ViewHolder> {

    private List<Category> categoryList;
    private LayoutInflater mInflater;
    private RecipeCategoryAdapter.ItemClickListener mClickListener;

    // data is passed into the constructor
    public RecipeCategoryAdapter(Context context, List<Category> data) {
        this.mInflater = LayoutInflater.from(context);
        this.categoryList = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cardview_recipe, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {@link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       /* if (getItemCount() == 0) {
            holder.myTextView1.setText("No recipes Yet");
        } else*/
        {
            holder.myTextView1.setText(categoryList.get(position).getRecipe_name());
            holder.myTextView2.setText(categoryList.get(position).getCategory_name());
            String image2 = categoryList.get(position).getImage2();
            Bitmap myBitmap = BitmapFactory.decodeFile(image2);

            if (myBitmap != null) {
                holder.imgImage.setImageBitmap(myBitmap);
            } else
                holder.imgImage.setImageResource(categoryList.get(position).getImage());
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    // allows clicks events to be caught
    void setClickListener(RecipeCategoryAdapter.ItemClickListener itemClickListener) {
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
        ImageView imgImage;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView1 = itemView.findViewById(R.id.txtDay);
            myTextView2 = itemView.findViewById(R.id.dayOfWeek);
            imgImage = itemView.findViewById(R.id.imgImage);
            itemView.setOnClickListener(this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Recipe.class);
                    intent.putExtra("NAME", myTextView1.getText().toString());
                    Log.d("myTextView1", myTextView1.getText().toString());
                    view.getContext().startActivity(intent);
                }
            });
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

