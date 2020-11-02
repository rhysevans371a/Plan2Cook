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
public class Search {

    private int id;
    private String recipe_name;
    private String course;
    private int preparation_time;
    private String category;
    private int image;
    private Double cost;
    private String image2;
    private String ingredient;

    public Search() {

    }

    public Search(String recipe_name, String course, int preparation_time, String category, int image, String image2, Double cost, String ingredient) {
        this.recipe_name = recipe_name;
        this.course = course;
        this.preparation_time = preparation_time;
        this.category = category;
        this.image = image;
        this.image2 = image2;
        this.cost = cost;
        this.ingredient = ingredient;
    }

    public Search(int id, String recipe_name, String course, int preparation_time, String category,
                  int image, String image2, Double cost, String ingredient) {
        this.id = id;
        this.recipe_name = recipe_name;
        this.course = course;
        this.preparation_time = preparation_time;
        this.category = category;
        this.image = image;
        this.image2 = image2;
        this.cost = cost;
        this.ingredient = ingredient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getPreparation_time() {
        return preparation_time;
    }

    public void setPreparation_time(int preparation_time) {
        this.preparation_time = preparation_time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}