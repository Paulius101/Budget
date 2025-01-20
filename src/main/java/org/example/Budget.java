package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import enums.Currency;
import enums.ExpenseCategory;
import enums.IncomeCategory;
import enums.PaymentMethod;
import org.example.enums.FileType;
import org.example.enums.ReadFromFileTypeCommand;
import org.example.enums.SaveToFileTypeCommand;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.example.enums.SaveToFileTypeCommand.TO_CSV;

public class Budget {
    private static final ArrayList<Record> RECORDS = new ArrayList<>();

    public static Long recordCount = 0L;

    public void populateTestData() {
        RECORDS.addAll(Arrays.asList(
                new ExpenseRecord(100.0, Currency.EUR, PaymentMethod.CARD, ExpenseCategory.GROCERIES, "Groceries", LocalDateTime.now().minusDays(7)),
                new ExpenseRecord(300.0, Currency.USD, PaymentMethod.CRYPTO, ExpenseCategory.TAXES, "Monthly rent", LocalDateTime.now().minusDays(30)),
                new ExpenseRecord(50.0, Currency.EUR, PaymentMethod.CASH, ExpenseCategory.ENTERTAINMENT, "Movie tickets", LocalDateTime.now().minusDays(3)),
                new IncomeRecord(1500.0, Currency.USD, IncomeCategory.SALARY, "Monthly salary", LocalDateTime.now().minusDays(10)),
                new IncomeRecord(200.0, Currency.USD, IncomeCategory.LOAN, "Freelance project", LocalDateTime.now().minusDays(5)),
                new IncomeRecord(50.0, Currency.EUR, IncomeCategory.GIFT, "Birthday gift", LocalDateTime.now().minusDays(2)),
                new IncomeRecord(1500.0, Currency.USD, IncomeCategory.SALARY, "Monthly salary", LocalDateTime.now().minusDays(10))
        ));
    }

    public void addRecord(Record record) {
        RECORDS.add(record);
    }

    public void replaceRecordById(Scanner sc) {
        System.out.println("Elemento atnaujinimas");
        System.out.println("Iveskite iraso ID kuri norite atnaujinti:");
        long id = Long.parseLong(sc.nextLine());
        System.out.println("Ar norite ivesti islaidu ar pajamu irasa?");
        System.out.println("[P] - pajamu irasas, [I] - islaidu irasas");
        Record updatedRecord;
        switch (sc.nextLine().toUpperCase()) {
            case "I" -> updatedRecord = new ExpenseRecord(sc, id);
            case "P" -> updatedRecord = new IncomeRecord(sc, id);
            default -> updatedRecord = new Record(sc, id);
        }
        replaceRecord(updatedRecord);
    }

    private void replaceRecord(Record record) {
        int index = RECORDS.indexOf(record);

        if (index == -1) {
            System.out.println("Irasas nerastas");
        }

        RECORDS.set(index, record);
    }

    public void editRecordById(Scanner sc) {
        System.out.println("Elemento redagavimas");
        Record recordToUpdate = getRecordById(sc);
        recordToUpdate.editRecord(sc);
    }

    public Record getRecordById(Scanner sc) {
        System.out.println("Iveskite elemento ID:");
        Long id = Long.parseLong(sc.nextLine());
        int index = RECORDS.indexOf(new Record(id));

        if (index == -1) {
            System.out.println("Irasas nerastas");
            return null;
        }

        return RECORDS.get(index);
    }

    public void deleteRecordById(Scanner sc) {
        System.out.println("Iveskite ID elemento, kuri norite istrinti:");
        Long id = Long.parseLong(sc.nextLine());
        if (deleteRecord(new Record(id))) {
            System.out.printf("Irasas [%s] buvo sekmingai istrintas%n", id);
            return;
        }
        System.out.printf("Irasas [%s] nerastas%n", id);
    }

    public boolean deleteRecord(Record recordToDelete) {
        return RECORDS.remove(recordToDelete);
    }

    public Double getBalance() {
        Double balance = 0.0;

        for (Record r : RECORDS) {
            if (r instanceof ExpenseRecord) {
                balance -= r.getAmount();
            }
            if (r instanceof IncomeRecord) {
                balance += r.getAmount();
            }
        }

        return balance;
    }

    private List<ExpenseRecord> getAllExpenses() {
        ArrayList<ExpenseRecord> expenses = new ArrayList<>();
        for (Record r : RECORDS) {
            if (r instanceof ExpenseRecord e) {
                expenses.add(e);
            }
        }
        return expenses;
    }

    private List<IncomeRecord> getAllIncomes() {
        ArrayList<IncomeRecord> incomes = new ArrayList<>();
        for (Record r : RECORDS) {
            if (r instanceof IncomeRecord i) {
                incomes.add(i);
            }
        }
        return incomes;
    }

    public void printAllExpenses() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("ISLAIDOS");
        for (ExpenseRecord e : getAllExpenses()) {
            System.out.println(e);
        }
        System.out.println("----------------------------------------------------------------");
    }

    public void printAllIncomes() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("PAJAMOS");
        for (IncomeRecord i : getAllIncomes()) {
            System.out.println(i);
        }
        System.out.println("----------------------------------------------------------------");
    }

    public void saveRecordsToFile(Scanner sc) throws IOException {
        switch (SaveToFileTypeCommand.selectCommand(sc)) {
            case TO_CSV -> File.saveDataToFile(FileType.CSV, RECORDS);
            case TO_JSON -> File.saveDataToFile(FileType.JSON, RECORDS);
        }
    }

    public void readRecordsFromFile(Scanner sc) throws JsonProcessingException {
        switch (ReadFromFileTypeCommand.selectCommand(sc)) {
            case FROM_CSV -> File.readDataFromFile(FileType.CSV);
            case FROM_JSON -> File.readDataFromFile(FileType.JSON);
        }
    }
}

