package dk.dtu.gbar.gitlab.shipment.persistence.dao;

import dk.dtu.gbar.gitlab.shipment.persistence.Connection;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Port;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Ship;
import dk.dtu.gbar.gitlab.shipment.persistence.search.SearchCriteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ShipDao extends Connection implements ShipDaoInterface {
    @Override
    public Ship getById(int id) {
        return getSession().get(Ship.class, id);
    }

    @Override
    public void save(Ship ship) {
        getSession().save(ship);
    }

    @Override
    public List<Ship> getAll() {
        return getSession().createQuery("from Ship ", Ship.class).list();
    }

    @Override
    public void deleteById(int id) {
        getSession().createQuery("DELETE from Ship WHERE id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public void deleteAll() {
        getSession().createSQLQuery("TRUNCATE TABLE SHIP AND COMMIT ").executeUpdate();
    }
    public List<Ship> search (SearchCriteria search){
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Ship> criteria = cb.createQuery(Ship.class);
        Root<Ship> root = criteria.from(Ship.class);
        Predicate predicate = cb.like(root.get(search.getFieldName()),search.getValue());
        criteria.select(root).where(predicate);
        return getSession().createQuery(criteria).getResultList();
    }
}
