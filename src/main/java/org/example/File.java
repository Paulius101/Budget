package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.enums.FileType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class File {

    public static void saveDataToFile(FileType type, ArrayList<Record> recordsList) throws IOException {
        String fileName = String.format("src/main/java/org/example/output.%s", type.getValue().toLowerCase());
        switch (type) {
            case CSV -> toCsv(fileName, recordsList);
            case JSON -> toJSON(fileName, recordsList);
        }
    }

    public static void readDataFromFile(FileType type) throws JsonProcessingException {
        String fileName = String.format("src/main/java/org/example/output.%s", type.getValue().toLowerCase());
        switch (type) {
            case CSV -> fromCSV(fileName);
            case JSON -> fromJSON(fileName);
        }
    }

    private static void toCsv(String fileName, ArrayList<Record> recordsList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Record r : recordsList) {
                writer.write(r.toString() + "\n");
            }
            System.out.printf("Isaugota i %s %n", fileName);
        } catch (IOException e) {
            System.out.println("Klaida rasant i faila: " + e.getMessage());
        }
    }

    private static void toJSON(String fileName, ArrayList<Record> recordsList) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.writeValue(new java.io.File(fileName), recordsList);
    }

    private static void fromCSV(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Klaida skaitant faila: " + e.getMessage());
        }
    }

    private static void fromJSON(String fileName) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Record> recordsDeserialised = objectMapper.readValue(fileName, new TypeReference<>() {
        });
        System.out.println(recordsDeserialised.toString());
    }
}
