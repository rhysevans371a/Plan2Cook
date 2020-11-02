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
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stu54259.plan2cook.Adapters.RecipeSearchAdapter;
import com.stu54259.plan2cook.Model.Search;
import com.stu54259.plan2cook.database.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

/**
 * All this code is created by Rhys Evans, STU54259.
 */
public class RecipeSearch extends MainActivity {

    EditText searchText;
    Spinner typeSpinner;
    List<Search> searchList = new ArrayList<>();
    SQLiteDatabase db;
    Cursor c;
    String searchType;
    RecyclerView listSearch;
    RecipeSearchAdapter adapterRecipe;
    ImageButton btnFavourite;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_search);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent a = new Intent(RecipeSearch.this, MainActivity.class);
                        startActivity(a);
                        break;
                    case R.id.recipes:
                        Intent b = new Intent(RecipeSearch.this, RecipeSearch.class);
                        startActivity(b);
                        break;
                    case R.id.shoppingList:
                        Intent c = new Intent(RecipeSearch.this, ShoppingList.class);
                        startActivity(c);
                        break;
                    case R.id.mealPlan:
                        Intent d = new Intent(RecipeSearch.this, MenuPlan.class);
                        startActivity(d);
                        break;
                    case R.id.reminder:
                        Intent e = new Intent(RecipeSearch.this, Reminders.class);
                        startActivity(e);
                        break;
                }
                return false;
            }
        });
        ImageButton clickSearch = findViewById(R.id.btnSearch);
        clickSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Button clicked", "button clicked");
                Search_Recipe();
                adapterRecipe.notifyDataSetChanged();
            }
        });
        ImageButton btnFavourite = findViewById(R.id.btnFavourites);
        btnFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Button clicked", "button clicked");
                Search_Favourites();
                adapterRecipe.notifyDataSetChanged();
            }
        });
        adapterRecipe = new RecipeSearchAdapter(this, searchList);
        listSearch = findViewById(R.id.list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        listSearch.setLayoutManager(mLayoutManager);
        listSearch.setItemAnimator(new DefaultItemAnimator());
        listSearch.setAdapter(adapterRecipe);

    }

    /**
     * All this code is created by Rhys Evans, STU54259.
     */
    public void Search_Favourites() {
        searchList.clear();
        searchText = findViewById(R.id.editSearch);
        typeSpinner = findViewById(R.id.typeSpinner);
        searchType = typeSpinner.getSelectedItem().toString();
        db = (new DatabaseManager(this).getWritableDatabase());

        String selectQuery = " SELECT A.recipe_name, B.image, B.image2, B.category, preparation_time, B.cost, C.ingredient " +
                "FROM " + DatabaseManager.TABLE_FAVOURITES + " " +
                "AS A JOIN " + DatabaseManager.TABLE_RECIPE + " AS B ON A.recipe_name = B.recipe_name" +
                " JOIN " + DatabaseManager.TABLE_QUANTITY + " AS C ON A.recipe_name = C.recipe GROUP BY A.recipe_name";
        c = db.rawQuery(selectQuery, null);
        Log.d("Query", selectQuery);
        if (c.moveToFirst()) {
            do {
                Search search = new Search();
                search.setRecipe_name(c.getString(c.getColumnIndex("recipe_name")));
                search.setImage(c.getInt(c.getColumnIndex("image")));
                search.setImage2(c.getString(c.getColumnIndex("image2")));
                search.setCategory(c.getString(c.getColumnIndex("category")));
                search.setPreparation_time(c.getInt(c.getColumnIndex("preparation_time")));
                search.setCost(c.getDouble(c.getColumnIndex("cost")));
                search.setIngredient(c.getString(c.getColumnIndex("ingredient")));
                searchList.add(search);
            } while (c.moveToNext());
            c.close();
        }

    }

    public void Search_Recipe() {
        searchList.clear();
        searchText = findViewById(R.id.editSearch);
        typeSpinner = findViewById(R.id.typeSpinner);
        searchType = typeSpinner.getSelectedItem().toString();
        db = (new DatabaseManager(this).getWritableDatabase());
        String selectQuery = "";
        String RECIPE_SEARCH = " SELECT DISTINCT A.recipe_name, A.image, A.image2, A.category, A.preparation_time, A.cost, B.ingredient " +
                "FROM " + DatabaseManager.TABLE_RECIPE + " AS A JOIN " + DatabaseManager.TABLE_QUANTITY + " AS B ON A.recipe_name = B.recipe";
        if (searchType.equals("preparation_time")) {

            selectQuery = RECIPE_SEARCH + " WHERE preparation_time <= " + searchText.getText().toString() + " GROUP BY A.recipe_name";
            c = db.rawQuery(selectQuery, null);
        } else if (searchType.equals("cost")) {
            selectQuery = RECIPE_SEARCH + " WHERE cost <= " + searchText.getText().toString() + " GROUP BY A.recipe_name";
            c = db.rawQuery(selectQuery, null);
        } else {
            selectQuery = RECIPE_SEARCH + " WHERE " + searchType + " LIKE ? GROUP BY  A.recipe_name";
            c = db.rawQuery(selectQuery, new String[]{"%" + searchText.getText().toString() + "%"});
        }
        Log.d("Query", selectQuery);
        /**
         * All this code is created by Rhys Evans, STU54259.
         */
        if (c.moveToFirst()) {
            do {
                Search search = new Search();
                search.setRecipe_name(c.getString(c.getColumnIndex("recipe_name")));
                search.setImage(c.getInt(c.getColumnIndex("image")));
                search.setImage2(c.getString(c.getColumnIndex("image2")));
                search.setCategory(c.getString(c.getColumnIndex("category")));
                search.setPreparation_time(c.getInt(c.getColumnIndex("preparation_time")));
                search.setCost(c.getDouble(c.getColumnIndex("cost")));
                search.setIngredient(c.getString(c.getColumnIndex("ingredient")));
                searchList.add(search);
            } while (c.moveToNext());
            c.close();
        }

    }


}