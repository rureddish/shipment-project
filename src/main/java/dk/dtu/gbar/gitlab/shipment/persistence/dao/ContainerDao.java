package dk.dtu.gbar.gitlab.shipment.persistence.dao;

import dk.dtu.gbar.gitlab.shipment.persistence.Connection;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Container;
import dk.dtu.gbar.gitlab.shipment.persistence.models.ContainerStatus;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Journey;
import dk.dtu.gbar.gitlab.shipment.persistence.search.SearchCriteria;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ContainerDao extends Connection implements ContainerDaoInterface {
    @Override
    public Container getById(int id) {
        return getSession().get(Container.class, id);
    }

    public Container getById(int id, boolean eager) {
        if(!eager) return getById(id);
        Container c = getSession().get(Container.class,id);
        Hibernate.initialize(c);
        return c;
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
    public List<ContainerStatus> getLastStatuses(Container container) {
        Query<ContainerStatus> query = getSession().createQuery("FROM ContainerStatus " +
                "WHERE journeyStatusParent = (FROM Journey WHERE journeyContainer = :container) " +
                "AND id in (SELECT MAX(id) FROM ContainerStatus " +
                "GROUP BY statusName)", ContainerStatus.class);
        query.setParameter("container", container);
        return query.list();
    }

    public List<Journey> getAllJourneys(Container container) {
        Query<Journey> query = getSession().createQuery("FROM Journey " +
                "WHERE journeyContainer =  :container ", Journey.class);
        query.setParameter("container", container);
        return query.list();
    }

    public Journey getCurrentJourney(Container container) {
        Query<Journey> query = getSession().createQuery("FROM Journey " +
                "WHERE journeyContainer =  :container AND sailStatus != 'FINISHED'", Journey.class);
        query.setParameter("container", container);
        return query.list().get(0);
    }

    public List<Container> search(SearchCriteria search) {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Container> criteria = cb.createQuery(Container.class);
        Root<Container> root = criteria.from(Container.class);
        Predicate predicate = cb.like(root.get(search.getFieldName()), search.getValue());
        criteria.select(root).where(predicate);
        return getSession().createQuery(criteria).getResultList();
    }


}
