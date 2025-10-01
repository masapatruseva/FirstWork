package cash;

public class CashCell {
    private final Cash cash;
    private int count;

    public CashCell(Cash cash, int count) {
        this.cash = cash;
        this.count = count;
    }

    public void addCash(int count) {
         this.count += count;
    }

    public void withdraw(int count) {
        if(this.count >= count) {
            this.count -=count;
        } else {
            throw new IllegalArgumentException("Не хватает купюр в ячейке");
        }
    }

    public void setCount(int count) {
        this.count = count;
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
