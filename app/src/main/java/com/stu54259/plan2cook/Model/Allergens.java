/*
 * ***************************************************************************************************************
 * Plan2Cook
 * Copyright (c) 2020
 *  Rhys Evans
 * STU54259 - Arden University
 * ***************************************************************************************************************
 */

package com.stu54259.plan2cook.Model;

public class Allergens {

    private int id;
    private String allergen_name;

    public Allergens() {

    }

    public Allergens(String allergen_name) {
        this.allergen_name = allergen_name;
    }

    public Allergens(int id, String allergen_name) {

        this.id = id;
        this.allergen_name = allergen_name;
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
}
