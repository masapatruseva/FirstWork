package command;

import atm.ATM;

public class GetBalanceCommand implements Command {
    @Override
    public int execute(ATM atm) {
        return atm.getBalance();
    }
}
