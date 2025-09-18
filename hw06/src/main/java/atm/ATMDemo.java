package atm;

import cash.Cash;
import strategy.GreedyStrategy;

import java.util.List;

public class ATMDemo {
    public static void main(String[] args) {
        ATM atm = new ATM(List.of(Cash.FIVE_THOUSAND, Cash.ONE_THOUSAND, Cash.ONE_HUNDRED, Cash.FIVE_HUNDRED), new GreedyStrategy());

        atm.getBalance();

        System.out.println("Кладем деньги");
        atm.addCash(Cash.ONE_HUNDRED, 2);
        atm.addCash(Cash.FIVE_HUNDRED, 3);
        atm.addCash(Cash.ONE_THOUSAND, 1);
        atm.addCash(Cash.FIVE_THOUSAND,2);

        System.out.println("Баланс: " + atm.getBalance());

        System.out.println("Снимаем 6100");
        atm.withdraw(6100);

        System.out.println("Баланс: " + atm.getBalance());
    }
}
