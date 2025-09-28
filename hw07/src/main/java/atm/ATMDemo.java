package atm;

import cash.Cash;
import department.ATMDepartment;
import strategy.GreedyStrategy;
import java.util.Map;

public class ATMDemo {
    public static void main(String[] args) {
        ATM atm1 = new ATM("firstATM", Map.of(Cash.FIVE_THOUSAND, 2, Cash.ONE_THOUSAND, 1, Cash.ONE_HUNDRED, 2, Cash.FIVE_HUNDRED, 3), new GreedyStrategy());
        ATM atm2 = new ATM("secondATM", Map.of(Cash.FIVE_THOUSAND, 4, Cash.ONE_HUNDRED, 6, Cash.FIFTY, 2), new GreedyStrategy());

        ATMDepartment department = new ATMDepartment();
        department.addATM(atm1);
        department.addATM(atm2);

        System.out.println("Общий баланс: " + department.getAllBalances());

        department.withdraw(6100);
        department.withdraw(15350);

        System.out.println("Общий баланс после снятия денег: " + department.getAllBalances());

        department.restoreAll();

        System.out.println("Общий баланс после восстановления: " + department.getAllBalances());

    }
}
