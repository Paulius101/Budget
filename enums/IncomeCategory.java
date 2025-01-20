package enums;

import java.util.Scanner;

public enum IncomeCategory {
    SALARY(1),
    LOAN(2),
    GIFT(3);

    private final Integer id;

    IncomeCategory(Integer id) {
        this.id = id;
    }

    public static IncomeCategory selectIncomeCategory(Scanner sc) {
        printIncomeCategoryList();
        System.out.println("Iveskite norimos pasirinkti pajamu kategorijos ID:");
        Integer id = Integer.parseInt(sc.nextLine());
        return getIncomeCategoryById(id);
    }

    public static IncomeCategory getIncomeCategoryById(Integer id) {
        for (IncomeCategory i : IncomeCategory.values()) {
            if (i.id.equals(id)) {
                return i;
            }
        }
        return null;
    }

    public static void printIncomeCategoryList() {
        for (IncomeCategory i : IncomeCategory.values()) {
            System.out.printf("[%d] - %s%n", i.id, i.name());
        }
    }
}
