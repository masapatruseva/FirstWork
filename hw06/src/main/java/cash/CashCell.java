package cash;

public class CashCell {
    private final Cash cash;
    private int count;

    public CashCell(Cash cash) {
        this.cash = cash;
        this.count = 0;
    }

    public void addCash(int count) {
         this.count += count;
    }

    public void withdraw(int count) {
        if(this.count >= count) {
            this.count -=count;
        }
    }

    public int getCount() {
        return count;
    }
    public int getBalance() {
        return cash.getValue() * count;
    }

    public Cash getCash() {
        return cash;
    }


}
