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
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stu54259.plan2cook.Adapters.SetImageAdapter;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

/**
 * All this code is created by Rhys Evans, STU54259.
 */
public class MainActivity extends AppCompatActivity {

    CarouselView carouselView;
    int[] sampleImages = {R.drawable.avocado_salad, R.drawable.chicken_tikka_curry, R.drawable.turkey_taco_bowls};
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    /**
     * All this code is created by Rhys Evans, STU54259.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent a = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(a);
                        break;
                    case R.id.recipes:
                        Intent b = new Intent(MainActivity.this, RecipeSearch.class);
                        startActivity(b);
                        break;
                    case R.id.shoppingList:
                        Intent c = new Intent(MainActivity.this, ShoppingList.class);
                        startActivity(c);
                        break;
                    case R.id.mealPlan:
                        Intent d = new Intent(MainActivity.this, MenuPlan.class);
                        startActivity(d);
                        break;
                    case R.id.reminder:
                        Intent e = new Intent(MainActivity.this, Reminders.class);
                        startActivity(e);
                        break;
                }
                return false;
            }
        });


        carouselView = findViewById(R.id.recipeImage);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Create Recipe Clicked.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, CreateRecipe.class);
                startActivity(intent);
            }
        });

        /**
         * All this code is created by Rhys Evans, STU54259.
         */
        GridView gv = findViewById(R.id.gridview);
        gv.setAdapter(new SetImageAdapter(getApplicationContext()));
        gv.setOnItemClickListener((parent, v, position, id) -> {

            Intent intent = new Intent(MainActivity.this, Category.class);
            intent.putExtra("Category", position); // put image data in Intent
            startActivity(intent); // start Intent

        });


    }


}