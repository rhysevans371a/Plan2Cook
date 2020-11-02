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
public class Category {

    private int id;
    private String category_name;
    private Integer image;
    private String recipe_name;
    private String image2;

    public Category() {

    }

    public Category(String category_name, Integer image, String image2, String recipe_name) {
        this.category_name = category_name;
        this.image = image;
        this.image2 = image2;
        this.recipe_name = recipe_name;
    }

    public Category(int id, String category_name, Integer image, String image2, String recipe_name) {

        this.id = id;
        this.category_name = category_name;
        this.image = image;
        this.image2 = image2;
        this.recipe_name = recipe_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }
}
