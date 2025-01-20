package org.example;

import enums.Currency;
import enums.IncomeCategory;

import java.time.LocalDateTime;
import java.util.Scanner;

public class IncomeRecord extends Record {
    private IncomeCategory category;

    public IncomeRecord(Double amount,
                        Currency currency,
                        IncomeCategory category,
                        String description,
                        LocalDateTime timestamp
    ) {
        super(amount, currency, description, timestamp);
        this.category = category;
    }

    public IncomeRecord(Scanner sc, long... id) {
        super(sc, id);
        this.category = IncomeCategory.selectIncomeCategory(sc);
    }

    public IncomeRecord(Long id) {
        super(id);
    }

    @Override
    public void editRecord(Scanner sc) {
        super.editRecord(sc);
        if (editParameter(sc, "category", this.category.name())) {
            System.out.println("Iveskite nauja reiksme:");
            this.category = IncomeCategory.selectIncomeCategory(sc);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] - P - %s %s",
                super.getId(),
                super.toString(),
                this.category
        );
    }
}
