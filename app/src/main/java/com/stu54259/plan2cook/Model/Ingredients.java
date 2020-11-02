/*
 * ***************************************************************************************************************
 * Plan2Cook
 * Copyright (c) 2020
 *  Rhys Evans
 * STU54259 - Arden University
 * ***************************************************************************************************************
 */

package com.stu54259.plan2cook.Model;

public class Ingredients {

    private int id;
    private String ingredient_name;
    private String description;
    private String measurement;
    private String ingredient_type;

    public Ingredients() {

    }

    public Ingredients(String ingredient_name, String description, String measurement, String ingredient_type) {

        this.ingredient_name = ingredient_name;
        this.description = description;
        this.measurement = measurement;
        this.ingredient_type = ingredient_type;
    }

    public Ingredients(int id, String ingredient_name, String description, String measurement, String ingredient_type) {

        this.id = id;
        this.ingredient_name = ingredient_name;
        this.description = description;
        this.measurement = measurement;
        this.ingredient_type = ingredient_type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public String getIngredient_type() {
        return ingredient_type;
    }

    public void setIngredient_type(String ingredient_type) {
        this.ingredient_type = ingredient_type;
    }
}
