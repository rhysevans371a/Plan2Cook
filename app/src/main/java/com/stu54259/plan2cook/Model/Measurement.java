/*
 * ***************************************************************************************************************
 * Plan2Cook
 * Copyright (c) 2020
 *  Rhys Evans
 * STU54259 - Arden University
 * ***************************************************************************************************************
 */

package com.stu54259.plan2cook.Model;

public class Measurement {

    private int id;
    private String measurement_name;

    public Measurement() {

    }

    public Measurement(String measurement_name) {

        this.measurement_name = measurement_name;
    }

    public Measurement(int id, String measurement_name) {

        this.id = id;
        this.measurement_name = measurement_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeasurement_name() {
        return measurement_name;
    }

    public void setMeasurement_name(String measurement_name) {
        this.measurement_name = measurement_name;
    }
}
