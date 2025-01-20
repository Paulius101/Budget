package org.example;

import enums.Currency;
import enums.ExpenseCategory;
import enums.PaymentMethod;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ExpenseRecord extends Record {
    private PaymentMethod paymentMethod;
    private ExpenseCategory category;

    public ExpenseRecord(Double amount,
                         Currency currency,
                         PaymentMethod paymentMethod,
                         ExpenseCategory category,
                         String description,
                         LocalDateTime timestamp) {
        super(amount, currency, description, timestamp);
        this.paymentMethod = paymentMethod;
        this.category = category;
    }

    public ExpenseRecord(Scanner sc, long... id) {
        super(sc, id);
        this.paymentMethod = PaymentMethod.selectPaymentMethod(sc);
        this.category = ExpenseCategory.selectExpenseCategory(sc);
    }

    public ExpenseRecord(Long id) {
        super(id);
    }

    @Override
    public void editRecord(Scanner sc) {
        super.editRecord(sc);
        if (editParameter(sc, "paymentMethod", this.paymentMethod.name())) {
            System.out.println("Iveskite nauja reiksme:");
            this.paymentMethod = PaymentMethod.selectPaymentMethod(sc);
        }

        if (editParameter(sc, "category", this.category.name())) {
            System.out.println("Iveskite nauja reiksme:");
            this.category = ExpenseCategory.selectExpenseCategory(sc);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] - I - %s %s %s",
                super.getId(),
                super.toString(),
                this.paymentMethod,
                this.category
        );
    }
}
