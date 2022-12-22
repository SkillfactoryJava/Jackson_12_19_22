package com.skillfactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillfactory.model.Car;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.core.JsonEncoding.UTF8;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Car> parsedCars = new LinkedList<>();
        File inputFile = new File("./data/car_array.json");
        File outFile = new File("./data/car_out.json");
        ObjectMapper objectMapper = new ObjectMapper();

        readJson(parsedCars, inputFile, objectMapper);

        for (Car car : parsedCars) {
            car.setColor("red");
        }
        System.out.println(parsedCars);


        writeJson(parsedCars, outFile, objectMapper);
    }

    private static void writeJson(List<Car> parsedCars, File outFile, ObjectMapper objectMapper) throws IOException {
        objectMapper.writer(new DefaultPrettyPrinter()).writeValue(outFile, parsedCars);
    }

    private static void readJson(List<Car> parsedCars, File inputFile, ObjectMapper objectMapper) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(inputFile);
        for (JsonNode currentNode : jsonNode) {
            parsedCars.add(new Car(currentNode.get("brand").asText(), currentNode.get("color").asText(), currentNode.get("model_year").asInt()));
        }
    }
}