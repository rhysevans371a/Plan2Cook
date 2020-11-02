/*
 * ***************************************************************************************************************
 * Plan2Cook
 * Copyright (c) 2020
 *  Rhys Evans
 * STU54259 - Arden University
 * ***************************************************************************************************************
 */

package com.stu54259.plan2cook.Model;

/**
 * All this code is created by Rhys Evans, STU54259.
 */
public class RecipeList {

    private Integer id;
    private Double ingredient_quantity;
    private String recipe;
    private String measurement_name;
    private String ingredient_name;
    private String description;
    private String ingredient_amount = String.valueOf(ingredient_quantity);

    public RecipeList() {

    }

    public RecipeList(String recipe, String ingredient_amount, String measurement_name, String ingredient_name, String description) {

        this.recipe = recipe;
        this.ingredient_amount = ingredient_amount;
        this.measurement_name = measurement_name;
        this.ingredient_name = ingredient_name;
        this.description = description;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getIngredient_amount() {
        return ingredient_amount;
    }

    public void setIngredient_amount(String ingredient_amount) {
        this.ingredient_amount = ingredient_amount;
    }

    public String getMeasurement_name() {
        return measurement_name;
    }

    public void setMeasurement_name(String measurement_name) {
        this.measurement_name = measurement_name;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return ingredient_amount + " " + measurement_name + " " + ingredient_name + " " + description;

    }
}
