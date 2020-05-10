package dk.dtu.gbar.gitlab.shipment.persistence.dao;

import dk.dtu.gbar.gitlab.shipment.persistence.Connection;
import dk.dtu.gbar.gitlab.shipment.persistence.models.ContainerStatus;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Journey;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Path;

import java.util.List;

public class ContainerStatusDao extends Connection implements ContainerStatusDaoInterface {
    @Override
    public ContainerStatus getById(int id) {
        return getSession().get(ContainerStatus.class, id);
    }

    @Override
    public void save(ContainerStatus containerStatus) {
        getSession().save(containerStatus);
    }

    @Override
    public List<ContainerStatus> getAll() {
        return getSession().createQuery("from ContainerStatus ", ContainerStatus.class).list();
    }

    @Override
    public void deleteById(int id) {
        getSession().createQuery("DELETE from ContainerStatus WHERE id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public void deleteAll() {

        getSession().createSQLQuery("TRUNCATE TABLE CONTAINER_STATUS AND COMMIT ").executeUpdate();
    }
}
