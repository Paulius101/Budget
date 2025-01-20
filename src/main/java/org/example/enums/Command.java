package org.example.enums;
import java.util.Scanner;

public enum Command {
    STOP_PROGRAM(0, "Stabdyti programa"),
    ADD_EXPENSE_RECORD(1, "Prideti nauja islaidu irasa"),
    ADD_INCOME_RECORD(2, "Prideti nauja pajamu irasa"),
    CHECK_BALANCE(3, "Patikrinti balansa"),
    PRINT_ALL_EXPENSES(4, "Atspausdinti visas islaidas"),
    PRINT_ALL_INCOMES(5, "Atspausdinti visas pajamas"),
    DELETE_RECORD_BY_ID(6, "Istrinti irasa pagal ID"),
    EDIT_RECORD_BY_ID(7, "Redaguoti irasa pagal ID"),
    REPLACE_RECORD_BY_ID(8, "Atnaujinti objekta pagal ID"),
    SAVE_DATA_TO_FILE(9, "Isaugoti duomenis i faila"),
    READ_DATA_FROM_FILE(10, "Gauti duomenis is failo");

    private final Integer id;
    private final String description;


    Command(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static Command selectCommand(Scanner sc) {
        printCommandList();
        System.out.println("Iveskite norimos pasirinkti komandos ID:");
        Integer id = Integer.parseInt(sc.nextLine());
        return getCommandById(id);
    }

    public static Command getCommandById(Integer id){
        for (Command c : Command.values()) {
            if (c.id.equals(id)){
                return c;
            }
        }
        return null;
    }

    public static void printCommandList() {
        for (Command c : Command.values()) {
            System.out.printf("[%d] - %s%n", c.id, c.description);
        }
    }

    public static boolean isAccepted(Scanner sc){
        System.out.println("[1] - taip, [2] - ne");
        String decision = sc.nextLine();
        return decision.equals("1");
    }
    
}
