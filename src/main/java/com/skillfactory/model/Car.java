package com.skillfactory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"model_year", "color", "brand"})
public class Car {
    private String brand;
    private String color;
    @JsonProperty("model_year")
    private Integer modelYear;

    public Car() {
    }

    public Car(String brand, String color, Integer modelYear) {
        this.brand = brand;
        this.color = color;
        this.modelYear = modelYear;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", modelYear=" + modelYear +
                '}';
    }
}
