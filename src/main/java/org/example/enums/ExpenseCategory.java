package enums;

import java.util.Scanner;

public enum ExpenseCategory {
    GROCERIES(1),
    TRANSPORT(2),
    TAXES(3),
    ENTERTAINMENT(4);

    private final Integer id;

    ExpenseCategory(Integer id) {
        this.id = id;
    }

    public static ExpenseCategory selectExpenseCategory(Scanner sc) {
        printExpenseCategoryList();
        System.out.println("Iveskite norimos pasirinkti islaidu kategorijos ID:");
        Integer id = Integer.parseInt(sc.nextLine());
        return getExpenseCategoryById(id);
    }

    public static ExpenseCategory getExpenseCategoryById(Integer id){
        for (ExpenseCategory e : ExpenseCategory.values()) {
            if (e.id.equals(id)){
                return e;
            }
        }
        return null;
    }

    public static void printExpenseCategoryList() {
        for (ExpenseCategory e : ExpenseCategory.values()) {
            System.out.printf("[%d] - %s%n", e.id, e.name());
        }
    }
}
