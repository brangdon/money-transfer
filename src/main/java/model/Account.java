package model;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public final class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "BALANCE")
    private BigDecimal balance;
    @Column(name = "EMAIL")
    private String email;

    public Account() {
    }

    public Account(BigDecimal balance, String email) {
        this.balance = balance;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (id != account.id) return false;
        if (!balance.equals(account.balance)) return false;
        return email.equals(account.email);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + balance.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", email='" + email + '\'' +
                '}';
    }
}
