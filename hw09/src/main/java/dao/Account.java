package dao;

import annotation.Id;

import java.math.BigDecimal;

public class Account {
    @Id
    private Long no;
    private String type;
    private BigDecimal rest;

    public Account() {}

    public Account(String type, BigDecimal rest) {
        this.type = type;
        this.rest = rest;
    }

    public Long getNo() { return no; }
    public void setNo(Long no) { this.no = no; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Number getRest() { return rest; }
    public void setRest(BigDecimal rest) { this.rest = rest; }

    @Override
    public String toString() {
        return "Account{" +
                "no=" + no +
                ", type='" + type + '\'' +
                ", rest=" + rest +
                '}';
    }
}
