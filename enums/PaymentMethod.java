package enums;

import java.util.Scanner;

public enum PaymentMethod {
    CARD(1),
    CASH(2),
    BANK_TRANSFER(3),
    CRYPTO(4);


    private final Integer id;

    PaymentMethod(Integer id) {
        this.id = id;
    }

    public static PaymentMethod selectPaymentMethod(Scanner sc) {
        printPaymentMethodList();
        System.out.println("Iveskite norimos pasirinkti mokejimu metodo ID:");
        Integer id = Integer.parseInt(sc.nextLine());
        return getPaymentMethodById(id);
    }

    public static PaymentMethod getPaymentMethodById(Integer id){
        for (PaymentMethod p : PaymentMethod.values()) {
            if (p.id.equals(id)){
                return p;
            }
        }
        return null;
    }

    public static void printPaymentMethodList() {
        for (PaymentMethod p : PaymentMethod.values()) {
            System.out.printf("[%d] - %s%n", p.id, p.name());
        }
    }
}
