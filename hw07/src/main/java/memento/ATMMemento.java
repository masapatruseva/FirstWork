package memento;

import atm.ATM;
import cash.Cash;

import java.util.HashMap;
import java.util.Map;

public class ATMMemento {
    private Map<Cash, Integer> saved;

    public ATMMemento(Map<Cash, Integer> lastSave) {
        saved = new HashMap<>(lastSave);
    }

    public Map<Cash, Integer> getSaved() {
        return saved;
    }
}
