package org.example.enums;

import java.util.Scanner;

public enum ReadFromFileTypeCommand {
        FROM_CSV(0, "Is CSV"),
        FROM_JSON(1, "Is JSON");

        private final Integer id;
        private final String description;

        ReadFromFileTypeCommand(Integer id, String description) {
            this.id = id;
            this.description = description;
        }

        public static ReadFromFileTypeCommand selectCommand(Scanner sc) {
            printCommandList();
            System.out.println("Iveskite norimos pasirinkti komandos ID:");
            Integer id = Integer.parseInt(sc.nextLine());
            return getCommandById(id);
        }

        public static ReadFromFileTypeCommand getCommandById(Integer id) {
            for (ReadFromFileTypeCommand c : ReadFromFileTypeCommand.values()) {
                if (c.id.equals(id)) {
                    return c;
                }
            }
            return null;
        }

        public static void printCommandList() {
            for (ReadFromFileTypeCommand c : ReadFromFileTypeCommand.values()) {
                System.out.printf("[%d] - %s%n", c.id, c.description);
            }
        }
}
