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
public class Shopping_List {

    private int id;
    private String ingredient_type;
    private String ingredient_name;
    private Double quantity;
    private String measurement_name;


    public Shopping_List() {

    }

    public Shopping_List(String ingredient_type, String ingredient_name, Double quantity, String measurement_name) {

        this.ingredient_type = ingredient_type;
        this.ingredient_name = ingredient_name;
        this.quantity = quantity;
        this.measurement_name = measurement_name;

    }

    public Shopping_List(int id, String ingredient_type, String ingredient_name, Double quantity, String measurement_name) {

        this.id = id;
        this.ingredient_type = ingredient_type;
        this.ingredient_name = ingredient_name;
        this.quantity = quantity;
        this.measurement_name = measurement_name;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIngredient_type() {
        return ingredient_type;
    }

    public void setIngredient_type(String ingredient_type) {
        this.ingredient_type = ingredient_type;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getMeasurement_name() {
        return measurement_name;
    }

    public void setMeasurement_name(String measurement_name) {
        this.measurement_name = measurement_name;
    }

    public String toString() {
        return quantity + "     " + measurement_name + "    " + ingredient_name + "        " + ingredient_type;
    }
}
