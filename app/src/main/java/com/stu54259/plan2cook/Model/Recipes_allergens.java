/*
 * ***************************************************************************************************************
 * Plan2Cook
 * Copyright (c) 2020
 *  Rhys Evans
 * STU54259 - Arden University
 * ***************************************************************************************************************
 */

package com.stu54259.plan2cook.Model;

public class Recipes_allergens {

    private int id;
    private String allergen_name;
    private int recipeID;

    public Recipes_allergens() {

    }

    public Recipes_allergens(String allergen_name, int recipeID) {
        this.allergen_name = allergen_name;
        this.recipeID = recipeID;
    }

    public Recipes_allergens(int id, String allergen_name, int recipeID) {
        this.id = id;
        this.allergen_name = allergen_name;
        this.recipeID = recipeID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAllergen_name() {
        return allergen_name;
    }

    public void setAllergen_name(String allergen_name) {
        this.allergen_name = allergen_name;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }
}
