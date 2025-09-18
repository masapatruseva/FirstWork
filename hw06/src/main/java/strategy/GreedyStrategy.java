package strategy;

import cash.Cash;
import cash.CashCell;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GreedyStrategy implements DispenseStrategy {
    @Override
    public Map<Cash, Integer> plan(int amount, Map<Cash, CashCell> cells) {
        Map<Cash, Integer> issuedCash = new HashMap<>();
        int remainder = amount;

        for (Cash c : cells.keySet().stream()
                .sorted((c1, c2) -> Integer.compare(c2.getValue(), c1.getValue()))
                .collect(Collectors.toList())) {

            CashCell cell = cells.get(c);
            int need = remainder / c.getValue();

            if((cell.getCount() >= need) && !(need == 0)) {
                issuedCash.put(cell.getCash(), need);
                remainder -= need * c.getValue();
            }
        }
        if(remainder > 0) {
            throw new IllegalArgumentException("Нельзя выдать: " + amount);
        }

        return issuedCash;
    }
}
