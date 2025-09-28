package strategy;

import cash.Cash;
import cash.CashCell;

import java.util.Map;

public interface DispenseStrategy {
     Map<Cash,Integer> plan(int amount, Map<Cash, CashCell> cells);
}
