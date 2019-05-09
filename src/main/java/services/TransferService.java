package services;

import error.TransferException;
import model.Account;
import model.Transfer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import validator.TransferValidator;

import java.util.List;

public class TransferService {

    private SessionFactory sessionFactory;

    public TransferService() {
        sessionFactory = new Configuration().configure()
                .buildSessionFactory();
    }

    public List<Transfer> getTransfers() {

        Session session = sessionFactory.openSession();
        List<Transfer> transfers = session.createQuery("FROM Transfer").list();
        session.close();

        return transfers;
    }

    public Transfer getTransferById(int transfer_id) {
        Session session = sessionFactory.openSession();
        Transfer transfer = session.get(Transfer.class, transfer_id);
        session.close();
        return transfer;
    }

    public Transfer moneyTransfer(Transfer transfer) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Account accountFrom = session.get(Account.class, transfer.getIdAccountFrom());
        Account accountTo = session.get(Account.class, transfer.getIdAccountTo());
        accountFrom.setBalance(accountFrom.getBalance().subtract(transfer.getAmount()));
        session.update(accountFrom);

        accountTo.setBalance(accountTo.getBalance().add(transfer.getAmount()));
        session.update(accountTo);

        session.save(transfer);
        session.getTransaction().commit();

        return transfer;
    }
}
