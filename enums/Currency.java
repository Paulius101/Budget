package enums;

import java.util.Scanner;

public enum Currency {
    EUR(1, "€"),
    USD(2, "$");

    private final Integer id;
    private final String symbol;

    Currency(Integer id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    public static Currency selectCurrency(Scanner sc) {
        printCurrencyList();
        System.out.println("Iveskite norimos pasirinkti valiutos ID:");
        Integer id = Integer.parseInt(sc.nextLine());
        return getCurrencyById(id);
    }

    public static Currency getCurrencyById(Integer id){
        for (Currency c : Currency.values()) {
            if (c.id.equals(id)){
                return c;
            }
        }
        return null;
    }

    public static void printCurrencyList() {
        for (Currency c : Currency.values()) {
            System.out.printf("[%d] - %s(%s)%n", c.id, c.name(), c.symbol);
        }
    }
}
