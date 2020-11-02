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
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stu54259.plan2cook.Adapters.ShoppingListAdapter;
import com.stu54259.plan2cook.Model.Shopping_List;
import com.stu54259.plan2cook.database.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

/**
 * All this code is created by Rhys Evans, STU54259.
 */
public class ShoppingList extends MainActivity {

    ShoppingListAdapter adapterRecipe;
    List<Shopping_List> shopList = new ArrayList<>();
    RecyclerView listIngredient;
    SQLiteDatabase db;
    Cursor c;
    EditText edittext;
    String search;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_list);
        edittext = findViewById(R.id.editPlanName);
        edittext.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                search = edittext.getText().toString();
                Log.d("Search value", search);
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    loadIngredient();
                    adapterRecipe.notifyDataSetChanged();
                    return true;
                }
                return false;
            }
        });
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent a = new Intent(ShoppingList.this, MainActivity.class);
                        startActivity(a);
                        break;
                    case R.id.recipes:
                        Intent b = new Intent(ShoppingList.this, RecipeSearch.class);
                        startActivity(b);
                        break;
                    case R.id.shoppingList:
                        Intent c = new Intent(ShoppingList.this, ShoppingList.class);
                        startActivity(c);
                        break;
                    case R.id.mealPlan:
                        Intent d = new Intent(ShoppingList.this, MenuPlan.class);
                        startActivity(d);
                        break;
                    case R.id.reminder:
                        Intent e = new Intent(ShoppingList.this, Reminders.class);
                        startActivity(e);
                        break;
                }
                return false;
            }
        });
        adapterRecipe = new ShoppingListAdapter(this, shopList);
        listIngredient = findViewById(R.id.listIngredient);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        listIngredient.setLayoutManager(mLayoutManager);
        listIngredient.setItemAnimator(new DefaultItemAnimator());
        listIngredient.setAdapter(adapterRecipe);
    }

    /**
     * All this code is created by Rhys Evans, STU54259.
     */
    public void loadIngredient() {
        shopList.clear();
        db = (new DatabaseManager(this).getWritableDatabase());
        String RECIPE_SEARCH =
                "SELECT SUM(A.ingredient_quantity) quantity, A.ingredient ingredient_name, A.recipe, B.ingredient_type, B.measurement_name, C.id, D.plan_name " +
                        "FROM " + DatabaseManager.TABLE_QUANTITY + " AS A JOIN " + DatabaseManager.TABLE_INGREDIENTS + " AS B ON A.ingredient = B.ingredient_name " +
                        "JOIN " + DatabaseManager.TABLE_PLAN_RECIPES + " AS C ON A.recipe = C.recipe_name " +
                        "JOIN " + DatabaseManager.TABLE_MEAL_PLAN + " AS D ON C.id = D.plan_recipe " +
                        "WHERE D.plan_name LIKE ? GROUP BY A.ingredient ORDER BY B.ingredient_type";
        Log.d("Search query", RECIPE_SEARCH);
        c = db.rawQuery(RECIPE_SEARCH, new String[]{"%" + search + "%"});

        if (c.moveToFirst()) {
            do {
                Shopping_List shopping_list = new Shopping_List();
                shopping_list.setQuantity(c.getDouble(c.getColumnIndex("quantity")));
                shopping_list.setIngredient_name(c.getString(c.getColumnIndex("ingredient_name")));
                shopping_list.setIngredient_type(c.getString(c.getColumnIndex("ingredient_type")));
                shopping_list.setMeasurement_name(c.getString(c.getColumnIndex("measurement_name")));
                shopList.add(shopping_list);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
    }
}
