package org.example.enums;

import java.util.Scanner;

public enum SaveToFileTypeCommand {
    TO_CSV(0, "I CSV"),
    TO_JSON(1, "I JSON");

    private final Integer id;
    private final String description;

    SaveToFileTypeCommand(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static SaveToFileTypeCommand selectCommand(Scanner sc) {
        printCommandList();
        System.out.println("Iveskite norimos pasirinkti komandos ID:");
        Integer id = Integer.parseInt(sc.nextLine());
        return getCommandById(id);
    }

    public static SaveToFileTypeCommand getCommandById(Integer id){
        for (SaveToFileTypeCommand c : SaveToFileTypeCommand.values()) {
            if (c.id.equals(id)){
                return c;
            }
        }
        return null;
    }

    public static void printCommandList() {
        for (SaveToFileTypeCommand c : SaveToFileTypeCommand.values()) {
            System.out.printf("[%d] - %s%n", c.id, c.description);
        }
    }
}
