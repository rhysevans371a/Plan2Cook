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
public class Recipe {

    private static String recipe_name;
    private static String description;
    private static String course;
    private static int servings;
    private static Double calories;
    private static int preparation_time;
    private static String method;
    private static String category;
    private static int image;
    private static Double cost;
    private static String image2;
    private int id;

    public Recipe() {

    }

    public Recipe(String recipe_name, String description, String course, int servings, Double calories,
                  int preparation_time, String method, String category, int image, String image2, Double cost) {
        Recipe.recipe_name = recipe_name;
        Recipe.description = description;
        Recipe.course = course;
        Recipe.servings = servings;
        Recipe.calories = calories;
        Recipe.preparation_time = preparation_time;
        Recipe.method = method;
        Recipe.category = category;
        Recipe.image = image;
        Recipe.image2 = image2;
        Recipe.cost = cost;
    }

    public Recipe(int id, String recipe_name, String description, String course, int servings, Double calories,
                  int preparation_time, String method, String category, int image, String image2, Double cost) {
        this.id = id;
        Recipe.recipe_name = recipe_name;
        Recipe.description = description;
        Recipe.course = course;
        Recipe.servings = servings;
        Recipe.calories = calories;
        Recipe.preparation_time = preparation_time;
        Recipe.method = method;
        Recipe.category = category;
        Recipe.image = image;
        Recipe.image2 = image2;
        Recipe.cost = cost;
    }

    public static String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        Recipe.image2 = image2;
    }

    public static String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        Recipe.recipe_name = recipe_name;
    }

    public static String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        Recipe.description = description;
    }

    public static String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        Recipe.course = course;
    }

    public static int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        Recipe.servings = servings;
    }

    public static Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        Recipe.calories = calories;
    }

    public static int getPreparation_time() {
        return preparation_time;
    }

    public void setPreparation_time(int preparation_time) {
        Recipe.preparation_time = preparation_time;
    }

    public static String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        Recipe.method = method;
    }

    public static Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        Recipe.cost = cost;
    }

    public static int getImage() {
        return image;
    }

    public void setImage(int image) {
        Recipe.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        Recipe.category = category;
    }
}
