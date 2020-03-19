package dk.dtu.gbar.gitlab.shipment.persistence.dao;

import dk.dtu.gbar.gitlab.shipment.persistence.Connection;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Container;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Status;
import org.hibernate.query.Query;

import java.util.List;

public class ContainerDao extends Connection implements ContainerDaoInterface {
    @Override
    public Container getById(int id) {
        return getSession().get(Container.class, id);
    }

    @Override
    public void save(Container container) {
        getSession().save(container);
    }

    @Override
    public List<Container> getAll() {
        return getSession().createQuery("from Container ", Container.class).list();
    }

    @Override
    public void deleteById(int id) {
        getSession().createQuery("DELETE from Container WHERE id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public void deleteAll() {
        getSession().createSQLQuery("TRUNCATE TABLE CONTAINER AND COMMIT ").executeUpdate();
    }

    @Override
    public List<Status> getLastStatuses(Container container) {
        Query<Status> query = getSession().createQuery("FROM Status " +
                "WHERE container = :id " +
                "AND id in (SELECT MAX(id) FROM Status " +
                "GROUP BY statusName)",Status.class);
        query.setParameter("id",container);
        return query.list();
    }
}
