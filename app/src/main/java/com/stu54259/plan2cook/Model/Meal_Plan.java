/*
 * ***************************************************************************************************************
 * Plan2Cook
 * Copyright (c) 2020
 *  Rhys Evans
 * STU54259 - Arden University
 * ***************************************************************************************************************
 */

package com.stu54259.plan2cook.Model;

public class Meal_Plan {
    private int id;
    private String plan_name;
    private Integer plan_recipe;


    public Meal_Plan() {

    }

    public Meal_Plan(String plan_name, Integer plan_recipe) {
        this.plan_name = plan_name;
        this.plan_recipe = plan_recipe;

    }

    public Meal_Plan(int id, String plan_name, Integer plan_recipe) {
        this.id = id;
        this.plan_name = plan_name;
        this.plan_recipe = plan_recipe;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlan_name() {
        return plan_name;
    }

    public void setPlan_name(String plan_name) {
        this.plan_name = plan_name;
    }

    public Integer getPlan_recipe() {
        return plan_recipe;
    }

    public void setPlan_recipe(Integer plan_recipe) {
        this.plan_recipe = plan_recipe;
    }
}
