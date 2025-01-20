package org.example;

import enums.Command;
import enums.Currency;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Scanner;

public class Record {
    private Long id;
    private Double amount;
    private Currency currency;
    private String description;
    private LocalDateTime timestamp;

    public Record(Long id) {
        this.id = id;
    }

    public Record(Double amount,
                  Currency currency,
                  String description,
                  LocalDateTime timestamp) {
        this.id = Budget.recordCount++;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.timestamp = timestamp;
    }

    public Record(Scanner sc, long... id) {
        if (id.length == 0){
            this.id = Budget.recordCount++;
        } else {
            this.id = id[0];
        }
        System.out.println("Iveskite suma:");
        this.amount = Double.parseDouble(sc.nextLine());
        this.currency = Currency.selectCurrency(sc);
        System.out.println("Iveskite islaidu iraso aprasymas:");
        this.description = sc.nextLine();
        this.timestamp = LocalDateTime.now();
    }

    public Double getAmount() {
        return amount;
    }

    public Long getId() {
        return id;
    }

    public void editRecord(Scanner sc) {
        if (editParameter(sc, "amount", String.valueOf(this.amount))) {
            System.out.println("Iveskite nauja reiksme:");
            this.amount = Double.parseDouble(sc.nextLine());
        }

        if (editParameter(sc, "currency", this.currency.name())) {
            System.out.println("Iveskite nauja reiksme:");
            this.currency = Currency.selectCurrency(sc);
        }

        if (editParameter(sc, "description", this.description)) {
            System.out.println("Iveskite nauja reiksme:");
            this.description = sc.nextLine();
        }
    }

    protected boolean editParameter(Scanner sc, String name, String value) {
        System.out.printf("%s: %s%n", name, value);
        System.out.printf("Keisti %s reiksme?%n", name);
        return Command.isAccepted(sc);
    }

    @Override
    public String toString() {
        return String.format(
                "%s %s%s %s",
                DateUtils.toFormattedString(this.timestamp),
                this.amount,
                this.currency,
                this.description
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        Record record = (Record) o;
        return Objects.equals(id, record.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
