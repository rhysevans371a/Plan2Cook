/*
 * ***************************************************************************************************************
 * Plan2Cook
 * Copyright (c) 2020
 *  Rhys Evans
 * STU54259 - Arden University
 * ***************************************************************************************************************
 */

package com.stu54259.plan2cook.Model;

public class Favourites {

    private int id;
    private int recipeID;

    public Favourites() {

    }

    public Favourites(int recipeID) {

        this.recipeID = recipeID;
    }

    public Favourites(int id, int recipeID) {

        this.id = id;
        this.recipeID = recipeID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }
}
