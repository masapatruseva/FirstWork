package atm;

import cash.Cash;
import cash.CashCell;

import java.util.*;
import java.util.stream.Collectors;

public class ATM {
    private Map<Cash, CashCell> cells = Arrays.stream(Cash.values())
            .collect(Collectors.toMap(c -> c, CashCell::new));

    public void addCash(Cash c, int amount) {
        cells.get(c).addCash(amount);
    }

    public void withgrow(int amount) {
        Map<Cash, Integer> res = new HashMap<>();
        int amount2 = amount;

        for (Cash c : Arrays.stream(Cash.values())
                .sorted((c1, c2) -> Integer.compare(c2.getValue(), c1.getValue()))
                .collect(Collectors.toList())) {

            CashCell cell = cells.get(c);
            int need = amount2 / c.getValue();
            int take = Math.min(need, cell.getCount());

            if(cell.getCount() > need && !(need == 0)) {
                res.put(cell.getCash(), need);
                amount2 -= need * c.getValue();
            }
        }
        if(amount2 > 0) {
            throw new IllegalArgumentException("Нельзя выдать: " + amount);
        }

        res.forEach((c, i) -> cells.get(c).withdraw(i));
    }

    public void getBalance() {
        int balance = cells.values().stream()
                .mapToInt(CashCell::getBalance)
                .sum();
        System.out.println("Баланс: " + balance + "p");
    }
}
