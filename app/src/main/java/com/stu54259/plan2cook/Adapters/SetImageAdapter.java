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
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.stu54259.plan2cook.R;

/**
 * All this code is created by Rhys Evans, STU54259.
 */
public class SetImageAdapter extends BaseAdapter {
    public static Integer[] categories = {
            R.drawable.african, R.drawable.american,
            R.drawable.asian, R.drawable.comfort,
            R.drawable.desserts, R.drawable.greek,
            R.drawable.italian, R.drawable.vegetarian,
            R.drawable.mexican,

    };
    private Context Cont;

    public SetImageAdapter(Context c) {
        Cont = c;
    }

    public int getCount() {
        return categories.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imgview = new ImageView(Cont);
        imgview.setLayoutParams(new GridView.LayoutParams(370, 250));
        imgview.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imgview.setPadding(10, 10, 10, 10);
        imgview.setImageResource(categories[position]);
        return imgview;

    }

}