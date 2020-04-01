package dk.dtu.gbar.gitlab.shipment.persistence.dao;

import dk.dtu.gbar.gitlab.shipment.persistence.Connection;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Container;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Port;
import dk.dtu.gbar.gitlab.shipment.persistence.search.SearchCriteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class PortDao extends Connection implements PortDaoInterface {
    @Override
    public Port getById(int id) {
        return getSession().get(Port.class, id);
    }

    @Override
    public void save(Port port) {
        getSession().save(port);
    }

    @Override
    public List<Port> getAll() {
        return getSession().createQuery("from Port ", Port.class).list();
    }

    @Override
    public void deleteById(int id) {
        getSession().createQuery("DELETE from Port WHERE id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public void deleteAll() {
        getSession().createSQLQuery("TRUNCATE TABLE PORT AND COMMIT ").executeUpdate();

    }
    public List<Port> search (SearchCriteria search){
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Port> criteria = cb.createQuery(Port.class);
        Root<Port> root = criteria.from(Port.class);
        Predicate predicate = cb.like(root.get(search.getFieldName()),search.getValue());
        criteria.select(root).where(predicate);
        return getSession().createQuery(criteria).getResultList();
    }
}
