package atm;

import cash.Cash;

public class ATMDemo {
    public static void main(String[] args) {
        ATM atm = new ATM();

        atm.getBalance();

        System.out.println("Кладем деньги");
        atm.addCash(Cash.ONE_HUNDRED, 2);
        atm.addCash(Cash.FIVE_HUNDRED, 3);
        atm.addCash(Cash.ONE_THOUSAND, 1);
        atm.addCash(Cash.FIVE_THOUSAND,2);

        atm.getBalance();

        System.out.println("Снимаем 610 рублей");
        atm.withgrow(6100);

        atm.getBalance();
    }
}
