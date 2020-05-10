package dk.dtu.gbar.gitlab.shipment.persistence.dao;

import dk.dtu.gbar.gitlab.shipment.persistence.Connection;
import dk.dtu.gbar.gitlab.shipment.persistence.models.ContainerStatus;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Path;
import dk.dtu.gbar.gitlab.shipment.persistence.models.PathPort;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Port;
import dk.dtu.gbar.gitlab.shipment.persistence.search.SearchCriteria;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class PathDao extends Connection implements PathDaoInterface {
    @Override
    public Path getById(int id) {
        return getSession().get(Path.class, id);
    }

    @Override
    public void save(Path path) {
        getSession().save(path);
    }

    @Override
    public List<Path> getAll() {

        return getSession().createQuery("from Path ", Path.class).list();
    }

    @Override
    public void deleteById(int id) {
        getSession().createQuery("DELETE from Path WHERE id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public void deleteAll() {
        getSession().createSQLQuery("TRUNCATE TABLE PORT AND COMMIT ").executeUpdate();
    }

    public List<Path> search(SearchCriteria search) {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Path> criteria = cb.createQuery(Path.class);
        Root<Path> root = criteria.from(Path.class);
        Predicate predicate = cb.like(root.get(search.getFieldName()), search.getValue());
        criteria.select(root).where(predicate);
        return getSession().createQuery(criteria).getResultList();
    }

    public PathPort start(Path path) {
        Query<PathPort> query = getSession().createQuery("FROM PathPort" +
                " WHERE pathParent =: path ORDER BY id ASC", PathPort.class).setMaxResults(1);
        query.setParameter("path", path);
        return query.list().get(0);
    }
}
