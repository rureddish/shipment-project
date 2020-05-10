package dk.dtu.gbar.gitlab.shipment.persistence.dao;

import dk.dtu.gbar.gitlab.shipment.persistence.Connection;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Container;
import dk.dtu.gbar.gitlab.shipment.persistence.models.PathPort;

import java.util.List;

public class PathPortDao extends Connection implements PathPortDaoInterface {
    @Override
    public PathPort getById(int id) {
        return getSession().get(PathPort.class, id);
    }

    @Override
    public void save(PathPort pathPort) {
        getSession().save(pathPort);
    }

    @Override
    public List<PathPort> getAll() {
        return getSession().createQuery("from Container ", PathPort.class).list();
    }

    @Override
    public void deleteById(int id) {
        getSession().createQuery("DELETE from PathPort WHERE id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public void deleteAll() {
        getSession().createSQLQuery("TRUNCATE TABLE PATH_PORT AND COMMIT ").executeUpdate();
    }
}
