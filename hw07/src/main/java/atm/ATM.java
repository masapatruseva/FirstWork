package atm;

import cash.Cash;
import cash.CashCell;
import department.Observer;
import memento.ATMMemento;
import strategy.DispenseStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ATM implements Handler {
    private Map<Cash, CashCell> cells;
    private String name;
    private ATMMemento saved;
    private DispenseStrategy strategy;
    private List<Observer> observers = new ArrayList<>();
    private Handler next;

    public ATM(String name, Map<Cash, Integer> available, DispenseStrategy strategy) {
        this.cells = new HashMap<>();
        for (Cash c : Cash.values()) {
            if (available.containsKey(c)) {
                cells.put(c, new CashCell(c, available.get(c)));
            } else {
                cells.put(c, new CashCell(c, 0));
            }
        }
        this.strategy = strategy;
        this.name = name;
        save();
        notifyObserver();
    }

    public String getName() {
        return name;
    }

    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    private void notifyObserver() {
        int balance = this.getBalance();
        observers.forEach(o -> o.balanceHasChange(this, balance));
    }

    public void addCash(Cash c, int amount) {
    cells.get(c).addCash(amount);
    notifyObserver();
    }

    public void withdraw(int amount) {
        Map<Cash, Integer> issuedCash = strategy.plan(amount, cells);
        issuedCash.forEach((c, i) -> cells.get(c).withdraw(i));

        notifyObserver();
    }

    public void save() {
        Map<Cash, Integer> state = cells.values().stream()
                .collect(Collectors.toMap(CashCell::getCash, CashCell::getCount));
        saved = new ATMMemento(state);
    }

    public void restore() {
        saved.getSaved().forEach((c, cnt) -> cells.get(c).setCount(cnt));
    }


    public int getBalance() {
        return cells.values().stream()
                .mapToInt(CashCell::getBalance)
                .sum();
    }

    @Override
    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public boolean handleWithdraw(int amount) {
        try {
            this.withdraw(amount);
            return true;
        } catch (IllegalArgumentException e) {
            if(next != null) {
                return next.handleWithdraw(amount);
            } else {
                return false;
            }
        }
    }
}
