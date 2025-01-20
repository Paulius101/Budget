package org.example;

import enums.Command;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean isActive = true;
        Budget budget = new Budget();
        budget.populateTestData();
        while (isActive) {
            try {
                switch (Command.selectCommand(sc)) {
                    case STOP_PROGRAM -> isActive = false;
                    case ADD_EXPENSE_RECORD -> budget.addRecord(new ExpenseRecord(sc));
                    case ADD_INCOME_RECORD -> budget.addRecord(new IncomeRecord(sc));
                    case CHECK_BALANCE -> System.out.printf("Balansas: %.2f Eur%n", budget.getBalance());
                    case PRINT_ALL_EXPENSES -> budget.printAllExpenses();
                    case PRINT_ALL_INCOMES -> budget.printAllIncomes();
                    case DELETE_RECORD_BY_ID -> budget.deleteRecordById(sc);
                    case EDIT_RECORD_BY_ID -> budget.editRecordById(sc);
                    case REPLACE_RECORD_BY_ID -> budget.replaceRecordById(sc);
                    case null, default -> System.out.println("Neatpazinta komanda");
                }
            } catch (Exception e) {
                System.out.println("Ivyko nenumatyta klaida:" + e.getMessage());
            }
        }
    }
}
