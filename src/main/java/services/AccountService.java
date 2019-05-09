package services;

import model.Account;
import model.Transfer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AccountService {
    private SessionFactory sessionFactory;

    public AccountService() {
        sessionFactory = new Configuration().configure()
                .buildSessionFactory();

    }


    public List<Account> getAccounts() {
        Session session = sessionFactory.openSession();
        List<Account> accounts = session.createQuery("FROM Account").list();
        session.close();
        return accounts;
    }

    public List<Transfer> getAccountTransfers(int accountId) {
        Session session = sessionFactory.openSession();
        List<Transfer> transfers = session.createQuery("FROM Transfer WHERE FROM_ACC = " + accountId).list();
        session.close();
        return transfers;

    }

    public Account addAccount(Account account) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(account);
        session.getTransaction().commit();
        session.close();
        return account;
    }

    public Account getAccount(int accountId) {
        Session session = sessionFactory.openSession();
        Account account = session.get(Account.class, accountId);
        session.close();
        return account;
    }

}
