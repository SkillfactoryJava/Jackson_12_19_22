package com.skillfactory;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.json.UTF8DataInputJsonParser;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.fasterxml.jackson.core.JsonEncoding.*;
import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;

public class Main {
    public static void main(String[] args) {
        HashMap<String, String> jsonValues = new HashMap<>();
        File inputFile = new File("./data/car.json");
        File outFile = new File("./data/car_out.json");
        JsonFactory jsonFactory = new JsonFactory();
        try (JsonParser parser = jsonFactory.createParser(inputFile)) {
            JsonToken jsonToken;
            do {
                jsonToken = parser.nextToken();
                if (FIELD_NAME.equals(jsonToken)) {
                    String key = parser.getValueAsString();
                    jsonToken = parser.nextToken();
                    jsonValues.put(key, parser.getValueAsString());
                }
            } while (jsonToken != null);
        } catch (
                IOException e) {
            System.out.println("Error during json parsing: " + e.getMessage());
        }

        System.out.println(jsonValues);
        jsonValues.put("color", "red");
        System.out.println(jsonValues);


        try (JsonGenerator generator = jsonFactory.createGenerator(outFile, UTF8)) {
            generator.setPrettyPrinter(new DefaultPrettyPrinter());
            generator.writeStartObject();
            for (Map.Entry<String, String> keyValuePair : jsonValues.entrySet()) {
                generator.writeStringField(keyValuePair.getKey(), keyValuePair.getValue());
            }
            generator.writeEndObject();
        } catch (IOException e) {
            System.out.println("Error during json generating: " + e.getMessage());
        }
    }
}