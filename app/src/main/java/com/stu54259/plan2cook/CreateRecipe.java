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
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stu54259.plan2cook.database.DatabaseManager;

/**
 * All this code is created by Rhys Evans, STU54259.
 */
public class CreateRecipe extends MainActivity {

    private static int RESULT_LOAD_IMAGE = 1;
    EditText RecipeName;
    EditText editDescription;
    EditText servingsText;
    EditText caloriesText;
    EditText prepTimeText;
    EditText costText;
    EditText editMethod;
    EditText ingQuantity;
    EditText ingName;
    EditText ingDescription;
    Spinner categorySpinner;
    Spinner courseSpinner;
    Spinner allergenText;
    Spinner measurementSpinner;
    Spinner ingredientType;
    DatabaseManager db;
    Integer image;
    String image2;
    String picturePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    Intent a = new Intent(CreateRecipe.this, MainActivity.class);
                    startActivity(a);
                    break;
                case R.id.recipes:
                    Intent b = new Intent(CreateRecipe.this, RecipeSearch.class);
                    startActivity(b);
                    break;
                case R.id.shoppingList:
                    Intent c = new Intent(CreateRecipe.this, ShoppingList.class);
                    startActivity(c);
                    break;
                case R.id.mealPlan:
                    Intent d = new Intent(CreateRecipe.this, MenuPlan.class);
                    startActivity(d);
                    break;
                case R.id.reminder:
                    Intent e = new Intent(CreateRecipe.this, Reminders.class);
                    startActivity(e);
                    break;
            }
            return false;
        });
        RecipeName = findViewById(R.id.editRecipeName);
        editDescription = findViewById(R.id.editDescription);
        servingsText = findViewById(R.id.servingsText);
        caloriesText = findViewById(R.id.caloriesText);
        prepTimeText = findViewById(R.id.prepTime);
        costText = findViewById(R.id.editCost);
        editMethod = findViewById(R.id.editMethod);
        ingQuantity = findViewById(R.id.ingQuantity);
        ingName = findViewById(R.id.ingName);
        ingDescription = findViewById(R.id.ingDescription);

        categorySpinner = findViewById(R.id.categorySpinner);
        courseSpinner = findViewById(R.id.courseSpinner);
        allergenText = findViewById(R.id.allergenText);
        measurementSpinner = findViewById(R.id.measurementSpinner);
        ingredientType = findViewById(R.id.typeSpinner);
        Button AddIngredient = findViewById(R.id.add);
        Button addImage = findViewById(R.id.image);
        Button create = findViewById(R.id.create);
        /**
         * All this code is created by Rhys Evans, STU54259.
         */
        AddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveIngredient();
                EmptyIngredientAfterDataInsert();
            }
        });

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRecipe();
                EmptyEditTextAfterDataInsert();
                Intent intent = new Intent(CreateRecipe.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int image = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(image);
            cursor.close();

        }
    }

    public void EmptyIngredientAfterDataInsert() {

        ingQuantity.getText().clear();
        ingName.getText().clear();
        ingDescription.getText().clear();


    }

    public void EmptyEditTextAfterDataInsert() {

        RecipeName.getText().clear();
        editDescription.getText().clear();
        servingsText.getText().clear();
        caloriesText.getText().clear();
        prepTimeText.getText().clear();
        costText.getText().clear();
        editMethod.getText().clear();

    }

    /**
     * All this code is created by Rhys Evans, STU54259.
     */
    public void saveIngredient() {

        String quantityTemp = ingQuantity.getText().toString();
        double quantity = 0.0;
        if (!"".equals(quantityTemp)) {
            quantity = Double.parseDouble(quantityTemp);
        }
        String recipe_name = RecipeName.getText().toString();
        String ingredient = ingName.getText().toString();
        String ingredientDescription = ingDescription.getText().toString();
        double ingredient_quantity = quantity;
        String measurement_name = measurementSpinner.getSelectedItem().toString();
        String ingredient_type = ingredientType.getSelectedItem().toString();

        db = new DatabaseManager(getApplicationContext());
        db.addIngredients(ingredient_quantity, recipe_name, ingredient, ingredient, ingredientDescription, measurement_name, ingredient_type);

    }

    /**
     * All this code is created by Rhys Evans, STU54259.
     */
    public void saveRecipe() {
        String recipe_name = RecipeName.getText().toString();
        String description = editDescription.getText().toString();
        String servingTemp = servingsText.getText().toString();
        Integer servings = 0;
        if (!"".equals(servingTemp)) {
            servings = Integer.parseInt(servingTemp);
        }
        String caloriesTemp = caloriesText.getText().toString();
        double calories = 0.0;
        if (!"".equals(caloriesTemp)) {
            calories = Double.parseDouble(caloriesTemp);
        }
        String preparation_temp = prepTimeText.getText().toString();
        Integer preparation_time = 0;
        if (!"".equals(preparation_temp)) {
            preparation_time = Integer.parseInt(preparation_temp);
        }
        String costTemp = costText.getText().toString();
        double cost = 0.0;
        if (!"".equals(costTemp)) {
            cost = Double.parseDouble(costTemp);
        }
        String method = editMethod.getText().toString();
        String category = categorySpinner.getSelectedItem().toString();
        String course = courseSpinner.getSelectedItem().toString();
        String allergens = allergenText.getSelectedItem().toString();
        image2 = picturePath;
        image = null;
        db = new DatabaseManager(getApplicationContext());
        db.addRecipe(recipe_name, description, course, servings, calories, preparation_time, method, category, image, image2, cost, allergens);

    }


}