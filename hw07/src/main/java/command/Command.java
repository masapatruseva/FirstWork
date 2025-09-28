package command;

import atm.ATM;

public interface Command {
    int execute(ATM atm);
}
