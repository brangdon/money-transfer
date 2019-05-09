package model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="FROM_ACC")
    private int accountFrom;
    @Column(name = "TO_ACC")
    private int accountTo;
    @Column(name = "AMOUNT")
    private BigDecimal amount;

    public Transfer() {
    }

    public Transfer(int accountFrom, int accountTo, BigDecimal amount) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(int accountFrom) {
        this.accountFrom = accountFrom;
    }

    public int getIdAccountTo() {
        return accountTo;
    }

    public void setAccountTo(int accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transfer transfer = (Transfer) o;

        if (id != transfer.id) return false;
        if (accountFrom != transfer.accountFrom) return false;
        if (accountTo != transfer.accountTo) return false;
        return amount.equals(transfer.amount);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + accountFrom;
        result = 31 * result + accountTo;
        result = 31 * result + amount.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + id +
                ", accountFrom=" + accountFrom +
                ", accountTo=" + accountTo +
                ", amount=" + amount +
                '}';
    }
}
