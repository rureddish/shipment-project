package dk.dtu.gbar.gitlab.shipment.persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Connection {
    private Session session;
    private Transaction transaction;

    public Session openSession() {
        return session = HibernateUtil.getSessionFactory().openSession();
    }

    public Transaction openTransaction() {
        return transaction=openSession().beginTransaction();
    }
    public void closeSession(){
        session.close();
    }
    public void closeTransaction(){
        transaction.commit();
        session.close();
    }

    public Session getSession() {
        return session;
    }

    public Transaction getTransaction() {
        return transaction;
    }
}
