package com.skillfactory;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.skillfactory.model.Car;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("./data/car_array.json");
        File outFile = new File("./data/car_out.json");

        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType carsListType = objectMapper.getTypeFactory().constructCollectionType(List.class, Car.class);

        List<Car> parsedCars = objectMapper.readValue(inputFile, carsListType);

        for (Car car : parsedCars) {
            car.setColor("red");
        }
        System.out.println(parsedCars);

        objectMapper.writer(new DefaultPrettyPrinter()).writeValue(outFile, parsedCars);
    }
}