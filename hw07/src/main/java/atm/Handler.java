package atm;

public interface Handler {
    void setNext(Handler next);
    boolean handleWithdraw(int amount);
}
