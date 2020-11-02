/*
 * ***************************************************************************************************************
 * Plan2Cook
 * Copyright (c) 2020
 *  Rhys Evans
 * STU54259 - Arden University
 * ***************************************************************************************************************
 */

package com.stu54259.plan2cook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stu54259.plan2cook.Adapters.RecipeCategoryAdapter;
import com.stu54259.plan2cook.Adapters.SetImageAdapter;
import com.stu54259.plan2cook.database.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

/**
 * All this code is created by Rhys Evans, STU54259.
 */

public class Category extends MainActivity {
    int category;
    Cursor c;
    List<com.stu54259.plan2cook.Model.Category> categoryList = new ArrayList<>();
    SQLiteDatabase db;
    RecyclerView recipeList;
    RecipeCategoryAdapter adapterRecipe;
    String search_name;
    int position = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_activity);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent a = new Intent(Category.this, MainActivity.class);
                        startActivity(a);
                        break;
                    case R.id.recipes:
                        Intent b = new Intent(Category.this, RecipeSearch.class);
                        startActivity(b);
                        break;
                    case R.id.shoppingList:
                        Intent c = new Intent(Category.this, ShoppingList.class);
                        startActivity(c);
                        break;
                    case R.id.mealPlan:
                        Intent d = new Intent(Category.this, MenuPlan.class);
                        startActivity(d);
                        break;
                    case R.id.reminder:
                        Intent e = new Intent(Category.this, Reminders.class);
                        startActivity(e);
                        break;
                }
                return false;
            }
        });
        position = getIntent().getIntExtra("Category", position);
        int selectedDrawable = SetImageAdapter.categories[position];
        search_name = getResources().getResourceEntryName(selectedDrawable);
        Log.e("position", search_name);

        loadRecipeList();

        adapterRecipe = new RecipeCategoryAdapter(this, categoryList);
        recipeList = findViewById(R.id.listSearch);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recipeList.setLayoutManager(mLayoutManager);
        recipeList.setItemAnimator(new DefaultItemAnimator());
        recipeList.setAdapter(adapterRecipe);

    }

    /**
     * All this code is created by Rhys Evans, STU54259.
     */
    public void loadRecipeList() {
        categoryList.clear();
        db = (new DatabaseManager(this).getWritableDatabase());
        String RECIPE_SEARCH = " SELECT recipe_name, image, image2, category " +
                "FROM " + DatabaseManager.TABLE_RECIPE;
        String selectQuery = "";
        selectQuery = RECIPE_SEARCH + " WHERE category LIKE ?";
        c = db.rawQuery(selectQuery, new String[]{"%" + search_name + "%"});
        if (c.moveToFirst()) {
            do {
                com.stu54259.plan2cook.Model.Category category = new com.stu54259.plan2cook.Model.Category();
                category.setRecipe_name(c.getString(c.getColumnIndex("recipe_name")));
                category.setImage(c.getInt(c.getColumnIndex("image")));
                category.setImage2(c.getString(c.getColumnIndex("image2")));
                category.setCategory_name(c.getString(c.getColumnIndex("category")));
                categoryList.add(category);
            } while (c.moveToNext());
            c.close();
        }

    }

}