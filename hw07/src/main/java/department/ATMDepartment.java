package department;

import atm.ATM;
import command.Command;
import command.GetBalanceCommand;

import java.util.ArrayList;
import java.util.List;

public class ATMDepartment implements Observer {
    private List<ATM> atms = new ArrayList<>();

    @Override
    public void balanceHasChange(ATM atm, int balance) {
        System.out.println("Новый баланс " + atm.getName() + ": " + balance);
    }

    public boolean withdraw(int amount) {
        if (atms.isEmpty()) {
            throw new IllegalStateException("В департаменте нет ATM");
        }
        return atms.get(0).handleWithdraw(amount);
    }

    public void restoreAll() {
        atms.forEach(ATM::restore);
    }

    public int getAllBalances() {
        Command cmd = new GetBalanceCommand();
        int allBalance = 0;
        for(ATM atm : atms) {
            allBalance += cmd.execute(atm);
        }
        return allBalance;
    }

    public void addATM(ATM atm) {
        atm.addObserver(this);
        atm.notifyObserver();
        if(!atms.isEmpty()) {
            atms.get(atms.size() - 1).setNext(atm);
        }
        atms.add(atm);
    }


}
