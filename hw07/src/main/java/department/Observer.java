package department;

import atm.ATM;

public interface Observer {
    void balanceHasChange(ATM atm, int balance);
}
