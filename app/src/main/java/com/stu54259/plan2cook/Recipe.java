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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stu54259.plan2cook.Adapters.RecyclerViewAdapter;
import com.stu54259.plan2cook.Model.RecipeList;
import com.stu54259.plan2cook.database.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

import static com.stu54259.plan2cook.database.DatabaseManager.TABLE_RECIPE;

/**
 * All this code is created by Rhys Evans, STU54259.
 */
public class Recipe extends MainActivity {

    ImageView recipeImage;
    TextView recipeName;
    TextView descriptionText;
    TextView courseText;
    TextView servingsText;
    TextView costText;
    TextView caloriesText;
    TextView methodText;
    TextView prepTime;
    RecyclerView listIngredient;
    SQLiteDatabase db;
    String search_name;
    Cursor c, cur;
    RecyclerViewAdapter adapterRecipe;
    List<RecipeList> itemRecipe = new ArrayList<>();
    List<com.stu54259.plan2cook.Model.Recipe> recipeDetail = new ArrayList<>();
    ImageButton button_favorite;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent a = new Intent(Recipe.this, MainActivity.class);
                        startActivity(a);
                        break;
                    case R.id.recipes:
                        Intent b = new Intent(Recipe.this, RecipeSearch.class);
                        startActivity(b);
                        break;
                    case R.id.shoppingList:
                        Intent c = new Intent(Recipe.this, ShoppingList.class);
                        startActivity(c);
                        break;
                    case R.id.mealPlan:
                        Intent d = new Intent(Recipe.this, MenuPlan.class);
                        startActivity(d);
                        break;
                    case R.id.reminder:
                        Intent e = new Intent(Recipe.this, Reminders.class);
                        startActivity(e);
                        break;
                }
                return false;
            }
        });
        search_name = getIntent().getStringExtra("NAME");


        // Display Ingredients
        loadIngredient();

        //recyclerview Recipe
        adapterRecipe = new RecyclerViewAdapter(this, itemRecipe);
        listIngredient = findViewById(R.id.listIngredient);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        listIngredient.setLayoutManager(mLayoutManager);
        listIngredient.setItemAnimator(new DefaultItemAnimator());
        listIngredient.setAdapter(adapterRecipe);

        loadRecipe();
        button_favorite = findViewById(R.id.button_favorite);
        button_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createFavourite();
                ((ImageButton) view).setImageResource(R.drawable.ic_favourite);

            }
        });
    }

    public void loadIngredient() {
        itemRecipe.clear();
        db = (new DatabaseManager(this).getWritableDatabase());
        String RECIPE_SEARCH = " SELECT A.recipe, A.ingredient_quantity, B.measurement_name, B.ingredient_name, B.description " +
                "FROM " + DatabaseManager.TABLE_QUANTITY + " AS A JOIN " + DatabaseManager.TABLE_INGREDIENTS +
                " AS B ON A.ingredient = B.ingredient_name";
        String selectQuery = "";
        selectQuery = RECIPE_SEARCH + " WHERE A.recipe LIKE ?";
        c = db.rawQuery(selectQuery, new String[]{"%" + search_name + "%"});
        if (c.moveToFirst()) {
            do {
                RecipeList recipeList = new RecipeList();
                recipeList.setRecipe(c.getString(c.getColumnIndex("recipe")));
                recipeList.setIngredient_amount(c.getString(c.getColumnIndex("ingredient_quantity")));
                recipeList.setMeasurement_name(c.getString(c.getColumnIndex("measurement_name")));
                recipeList.setIngredient_name(c.getString(c.getColumnIndex("ingredient_name")));
                recipeList.setDescription(c.getString(c.getColumnIndex("description")));
                itemRecipe.add(recipeList);
            } while (c.moveToNext());
            c.close();
        }

    }


    public void loadRecipe() {
        recipeDetail.clear();
        db = (new DatabaseManager(this).getWritableDatabase());
        String query = "";
        String LOAD_RECIPE = " SELECT recipe_name, description, course, servings, calories, preparation_time, method, category, image, image2, cost FROM " + TABLE_RECIPE;
        query = LOAD_RECIPE + " WHERE recipe_name LIKE ?";
        cur = db.rawQuery(query, new String[]{"%" + search_name + "%"});
        if (cur.moveToFirst()) {
            do {
                com.stu54259.plan2cook.Model.Recipe recipe = new com.stu54259.plan2cook.Model.Recipe();
                recipe.setRecipe_name(cur.getString(cur.getColumnIndex("recipe_name")));
                recipe.setDescription(cur.getString(cur.getColumnIndex("description")));
                recipe.setCourse(cur.getString(cur.getColumnIndex("course")));
                recipe.setServings(cur.getInt(cur.getColumnIndex("servings")));
                recipe.setCalories(cur.getDouble(cur.getColumnIndex("calories")));
                recipe.setPreparation_time(cur.getInt(cur.getColumnIndex("preparation_time")));
                recipe.setMethod(cur.getString(cur.getColumnIndex("method")));
                recipe.setCategory(cur.getString(cur.getColumnIndex("category")));
                recipe.setImage(cur.getInt(cur.getColumnIndex("image")));
                recipe.setImage2(cur.getString(cur.getColumnIndex("image2")));
                recipe.setCost(cur.getDouble(cur.getColumnIndex("cost")));
                recipeDetail.add(recipe);
            } while (c.moveToNext());
            cur.close();
        }
        String name = com.stu54259.plan2cook.Model.Recipe.getRecipe_name();
        recipeName = findViewById(R.id.recipeName);
        recipeName.setText(name);
        String desc = com.stu54259.plan2cook.Model.Recipe.getDescription();
        descriptionText = findViewById(R.id.descriptionText);
        descriptionText.setText(desc);
        String course = com.stu54259.plan2cook.Model.Recipe.getCourse();
        courseText = findViewById(R.id.courseText);
        courseText.setText("Course: " + course);
        int serve = com.stu54259.plan2cook.Model.Recipe.getServings();
        servingsText = findViewById(R.id.servingsText);
        servingsText.setText("Servings: " + serve);
        Double calories = com.stu54259.plan2cook.Model.Recipe.getCalories();
        caloriesText = findViewById(R.id.caloriesText);
        caloriesText.setText("Calories: " + calories);
        Double cost = com.stu54259.plan2cook.Model.Recipe.getCost();
        costText = findViewById(R.id.costText);
        costText.setText("Cost £ " + cost + "0");
        int prep_time = com.stu54259.plan2cook.Model.Recipe.getPreparation_time();
        prepTime = findViewById(R.id.prepTime);
        prepTime.setText("Preparation time " + prep_time + " mins");
        String method = com.stu54259.plan2cook.Model.Recipe.getMethod();
        method = method.replaceAll("\\.\\s?", ".\n");
        methodText = findViewById(R.id.methodText);
        methodText.setText(method);

        int image = com.stu54259.plan2cook.Model.Recipe.getImage();
        recipeImage = findViewById(R.id.recipeImage);
        recipeImage.setImageResource(image);
        String image2 = com.stu54259.plan2cook.Model.Recipe.getImage2();
        Log.e("Image 2", image2);
        recipeImage = findViewById(R.id.recipeImage);
        Bitmap myBitmap = BitmapFactory.decodeFile(image2);
        if (myBitmap != null)
            recipeImage.setImageBitmap(myBitmap);
        else
            recipeImage.setImageResource(image);


    }

    /**
     * All this code is created by Rhys Evans, STU54259.
     */
    public void createFavourite() {
        DatabaseManager db = new DatabaseManager(this);
        db.addFavourite(search_name);
        Toast.makeText(getApplicationContext(),
                "Added to Favourites", Toast.LENGTH_SHORT).show();
    }

}