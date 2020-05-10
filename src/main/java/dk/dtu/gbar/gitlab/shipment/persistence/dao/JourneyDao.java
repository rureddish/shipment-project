package dk.dtu.gbar.gitlab.shipment.persistence.dao;

import dk.dtu.gbar.gitlab.shipment.persistence.Connection;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Journey;
import dk.dtu.gbar.gitlab.shipment.persistence.search.SearchCriteria;
import org.hibernate.Hibernate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class JourneyDao extends Connection implements JourneyDaoInterface {
    @Override
    public Journey getById(int id) {
        return getSession().get(Journey.class,id);
    }
    @Override
    public Journey getById(int id, boolean eager) {
        if(!eager) return getById(id);
        Journey j = getSession().get(Journey.class,id);
        Hibernate.initialize(j);
        return j;
    }

    @Override
    public void save(Journey journey) {
        getSession().save(journey);
    }

    @Override
    public List<Journey> getAll() {
        return getSession().createQuery("from Journey ", Journey.class).list();
    }

    @Override
    public void deleteById(int id) {
        getSession().createQuery("DELETE from Journey WHERE id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public void deleteAll() {
        getSession().createSQLQuery("TRUNCATE TABLE JOURNEY AND COMMIT ").executeUpdate();
    }
    public List<Journey> search (SearchCriteria search){
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Journey> criteria = cb.createQuery(Journey.class);
        Root<Journey> root = criteria.from(Journey.class);
        Predicate predicate = cb.like(root.get(search.getFieldName()),search.getValue());
        criteria.select(root).where(predicate);
        return getSession().createQuery(criteria).getResultList();
    }
}
