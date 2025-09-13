package cash;

public enum Cash {

    FIVE_THOUSAND(5000),
    ONE_THOUSAND(1000),
    FIVE_HUNDRED(500),
    ONE_HUNDRED(100);

    private final int value;

    Cash(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
