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
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stu54259.plan2cook.Adapters.MenuPlanAdapter;
import com.stu54259.plan2cook.Model.Plan_Recipes;
import com.stu54259.plan2cook.database.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

/**
 * All this code is created by Rhys Evans, STU54259.
 */
public class MenuPlan extends MainActivity {
    SQLiteDatabase db;
    Cursor c;
    RecyclerView planList;
    MenuPlanAdapter setAdapter;
    Button newPlan;
    List<Plan_Recipes> menuPlan = new ArrayList<>();
    String search;
    EditText edittext;
    TextView planTitle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_plan);
        planTitle = findViewById(R.id.planTitle);
        edittext = findViewById(R.id.editPlanName);
        edittext.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                search = edittext.getText().toString();
                Log.d("Search value", search);
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    WeekView();
                    setAdapter.notifyDataSetChanged();
                    //planTitle.setText(menuPlan.get(position).getPlan_name());
                    Log.d("Size", String.valueOf(menuPlan.size()));
                    return true;
                }
                return false;
            }
        });
        /**
         * All this code is created by Rhys Evans, STU54259.
         */
        findViewById(R.id.newPlan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MenuPlan.this, "Create New Plan.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MenuPlan.this, CreateMealPlan.class);
                startActivity(intent);
            }
        });
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent a = new Intent(MenuPlan.this, MainActivity.class);
                        startActivity(a);
                        break;
                    case R.id.recipes:
                        Intent b = new Intent(MenuPlan.this, RecipeSearch.class);
                        startActivity(b);
                        break;
                    case R.id.shoppingList:
                        Intent c = new Intent(MenuPlan.this, ShoppingList.class);
                        startActivity(c);
                        break;
                    case R.id.mealPlan:
                        Intent d = new Intent(MenuPlan.this, MenuPlan.class);
                        startActivity(d);
                        break;
                    case R.id.reminder:
                        Intent e = new Intent(MenuPlan.this, Reminders.class);
                        startActivity(e);
                        break;
                }
                return false;
            }
        });
        setAdapter = new MenuPlanAdapter(this, menuPlan);
        planList = findViewById(R.id.listSearch);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        planList.setLayoutManager(mLayoutManager);
        planList.setItemAnimator(new DefaultItemAnimator());
        planList.setAdapter(setAdapter);
    }

    /**
     * All this code is created by Rhys Evans, STU54259.
     */
    public void WeekView() {

        db = (new DatabaseManager(this).getWritableDatabase());
        String PlanSearch = " SELECT DISTINCT A.date, A.date_string, A.dayOfWeek, A.recipe_name, B.image, B.image2, C.plan_name " +
                "FROM " + DatabaseManager.TABLE_PLAN_RECIPES + " AS A JOIN " + DatabaseManager.TABLE_RECIPE + " AS B ON A.recipe_name = B.recipe_name " +
                "JOIN " + DatabaseManager.TABLE_MEAL_PLAN + " AS C ON A.id = C.plan_recipe " + " WHERE C.plan_name LIKE ? GROUP BY A.recipe_name";
        c = db.rawQuery(PlanSearch, new String[]{"%" + search + "%"});
        Log.d("Query", PlanSearch);
        if (c.moveToFirst()) {
            do {
                Plan_Recipes planRecipe = new Plan_Recipes();
                planRecipe.setDate(c.getInt(c.getColumnIndex("date")));
                planRecipe.setDate_string(c.getString(c.getColumnIndex("date_string")));
                planRecipe.setDayOfWeek(c.getString(c.getColumnIndex("dayOfWeek")));
                planRecipe.setRecipe_name(c.getString(c.getColumnIndex("recipe_name")));
                planRecipe.setPlan_name(c.getString(c.getColumnIndex("plan_name")));
                planRecipe.setImage(c.getInt(c.getColumnIndex("image")));
                planRecipe.setImage2(c.getString(c.getColumnIndex("image2")));
                menuPlan.add(planRecipe);
            } while (c.moveToNext());
            c.close();
        }
    }

}
