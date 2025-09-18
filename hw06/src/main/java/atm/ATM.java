package atm;

import cash.Cash;
import cash.CashCell;
import strategy.DispenseStrategy;

import java.util.*;
import java.util.stream.Collectors;

public class ATM {
    private Map<Cash, CashCell> cells;
    private DispenseStrategy strategy;

    public ATM(List<Cash> available, DispenseStrategy strategy) {
        cells = available.stream()
                .collect(Collectors.toMap(c -> c, CashCell::new));
        this.strategy = strategy;
    }

    public void addCash(Cash c, int amount) {
        cells.get(c).addCash(amount);
    }

    public void withdraw(int amount) {
        Map<Cash, Integer> issuedCash = strategy.plan(amount, cells);
        issuedCash.forEach((c, i) -> cells.get(c).withdraw(i));
    }

    public int getBalance() {
        return cells.values().stream()
                .mapToInt(CashCell::getBalance)
                .sum();
    }
}
