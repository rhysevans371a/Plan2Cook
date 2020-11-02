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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stu54259.plan2cook.Adapters.RecipeListAdapter;
import com.stu54259.plan2cook.database.DatabaseManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * All this code is created by Rhys Evans, STU54259.
 */
public class CreateMealPlan extends MainActivity {

    DatePicker datepicker;
    Button submit;
    List<com.stu54259.plan2cook.Model.Category> listRecipe = new ArrayList<>();
    Cursor c;
    RecyclerView recipeList;
    RecipeListAdapter adapterRecipe;
    String recipe_name;
    EditText editPlanName;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_meal_plan);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent a = new Intent(CreateMealPlan.this, MainActivity.class);
                        startActivity(a);
                        break;
                    case R.id.recipes:
                        Intent b = new Intent(CreateMealPlan.this, RecipeSearch.class);
                        startActivity(b);
                        break;
                    case R.id.shoppingList:
                        Intent c = new Intent(CreateMealPlan.this, ShoppingList.class);
                        startActivity(c);
                        break;
                    case R.id.mealPlan:
                        Intent d = new Intent(CreateMealPlan.this, MenuPlan.class);
                        startActivity(d);
                        break;
                    case R.id.reminder:
                        Intent e = new Intent(CreateMealPlan.this, Reminders.class);
                        startActivity(e);
                        break;
                }
                return false;
            }
        });
        datepicker = findViewById(R.id.calendarView);
        ListRecipes();
        RecipeListAdapter.OnRecipeClickListener listener = new RecipeListAdapter.OnRecipeClickListener() {
            public void onRecipeClicked(int position, String recName) {
                Log.d("Recipe selected", recName);
                recipe_name = recName;
            }
            /**
             * All this code is created by Rhys Evans, STU54259.
             */
        };
        adapterRecipe = new RecipeListAdapter(this, listRecipe, listener);
        recipeList = findViewById(R.id.recipes);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recipeList.setLayoutManager(mLayoutManager);
        recipeList.setItemAnimator(new DefaultItemAnimator());
        recipeList.setAdapter(adapterRecipe);

        submit = findViewById(R.id.create);

        // perform click event on submit button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreatePlan();
                Toast.makeText(getApplicationContext(),
                        "Recipe added to plan", Toast.LENGTH_SHORT).show();

            }

        });
    }

    public void ListRecipes() {
        listRecipe.clear();
        SQLiteDatabase db = (new DatabaseManager(this).getWritableDatabase());
        String selectQuery = " SELECT recipe_name, image, image2, category" + " FROM " + DatabaseManager.TABLE_RECIPE + "  GROUP BY recipe_name";
        c = db.rawQuery(selectQuery, null);
        Log.d("Query", selectQuery);
        if (c.moveToFirst()) {
            do {
                com.stu54259.plan2cook.Model.Category category = new com.stu54259.plan2cook.Model.Category();
                category.setRecipe_name(c.getString(c.getColumnIndex("recipe_name")));
                category.setImage(c.getInt(c.getColumnIndex("image")));
                category.setImage2(c.getString(c.getColumnIndex("image2")));
                category.setCategory_name(c.getString(c.getColumnIndex("category")));
                listRecipe.add(category);
            } while (c.moveToNext());
            c.close();
        }

    }

    /**
     * All this code is created by Rhys Evans, STU54259.
     */
    public void CreatePlan() {
        editPlanName = findViewById(R.id.editPlanName);
        String plan_name = editPlanName.getText().toString();
        DatabaseManager db;
        String dayOfTheWeek;
        int day = datepicker.getDayOfMonth();
        int month = datepicker.getMonth();
        int year = datepicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Integer d_name = day;
        Integer plan_recipe = 0;
        Log.d("Date", String.valueOf(d_name));
        dayOfTheWeek = sdf.format(calendar.getTime());
        String date = day + "/" + month + "/" + year;
        db = new DatabaseManager(getApplicationContext());
        Log.d("Recipe name", recipe_name);
        db.createPlanRecipe(d_name, date, dayOfTheWeek, recipe_name);
        db.createPlan(plan_name, plan_recipe);

    }


}
