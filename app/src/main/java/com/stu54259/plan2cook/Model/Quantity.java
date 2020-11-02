/*
 * ***************************************************************************************************************
 * Plan2Cook
 * Copyright (c) 2020
 *  Rhys Evans
 * STU54259 - Arden University
 * ***************************************************************************************************************
 */

package com.stu54259.plan2cook.Model;

public class Quantity {
    private int id;
    private Double ingredient_quantity;
    private String recipe;
    private String ingredient;

    public Quantity() {

    }

    public Quantity(Double ingredient_quantity, String recipe, String ingredient) {

        this.ingredient_quantity = ingredient_quantity;
        this.recipe = recipe;
        this.ingredient = ingredient;
    }

    public Quantity(int id, Double ingredient_quantity, String recipe, String ingredient) {

        this.id = id;
        this.ingredient_quantity = ingredient_quantity;
        this.recipe = recipe;
        this.ingredient = ingredient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getIngredient_quantity() {
        return ingredient_quantity;
    }

    public void setIngredient_quantity(Double ingredient_quantity) {
        this.ingredient_quantity = ingredient_quantity;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}