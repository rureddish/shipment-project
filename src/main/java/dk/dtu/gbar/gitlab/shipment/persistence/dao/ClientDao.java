package dk.dtu.gbar.gitlab.shipment.persistence.dao;

import dk.dtu.gbar.gitlab.shipment.persistence.Connection;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Client;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.lang.ref.Cleaner;
import java.util.List;

public class ClientDao extends Connection implements ClientInterface {
    @Override
    public Client getById(int id) {
        return getSession().get(Client.class, id);
    }

    @Override
    public Client getById(int id, boolean eager) {
        if(!eager) return getById(id);
        Client c = getSession().get(Client.class,id);
        Hibernate.initialize(c);
        return c;
    }

    @Override
    public void save(Client client) {
        getSession().save(client);
    }

    @Override
    public List<Client> getAll() {
        return getSession().createQuery("from Client ", Client.class).list();
    }

    @Override
    public void deleteById(int id) {
        getSession().createQuery("DELETE from Client WHERE id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public void deleteAll() {
        getSession().createSQLQuery("TRUNCATE TABLE CLIENT AND COMMIT").executeUpdate();

    }
}
