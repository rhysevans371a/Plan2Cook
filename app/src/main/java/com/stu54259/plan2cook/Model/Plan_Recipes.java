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
public class Plan_Recipes {

    private int id;
    private Integer date;
    private String date_string;
    private String dayOfWeek;
    private String recipe_name;
    private String plan_name;
    private Integer image;
    private String image2;

    public Plan_Recipes() {

    }

    public Plan_Recipes(Integer date, String date_string, String dayOfWeek, String recipe_name, String plan_name, Integer image, String image2) {

        this.date = date;
        this.date_string = date_string;
        this.dayOfWeek = dayOfWeek;
        this.recipe_name = recipe_name;
        this.plan_name = plan_name;
        this.image = image;
        this.image2 = image2;
    }

    public Plan_Recipes(int id, Integer date, String date_string, String dayOfWeek, String recipe_name, String plan_name, Integer image, String image2) {

        this.id = id;
        this.date = date;
        this.date_string = date_string;
        this.dayOfWeek = dayOfWeek;
        this.recipe_name = recipe_name;
        this.plan_name = plan_name;
        this.image = image;
        this.image2 = image2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getDate_string() {
        return date_string;
    }

    public void setDate_string(String date_string) {
        this.date_string = date_string;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
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
}
