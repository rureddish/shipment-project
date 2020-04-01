package dk.dtu.gbar.gitlab.shipment.persistence.service;

import dk.dtu.gbar.gitlab.shipment.persistence.dao.ContainerDao;
import dk.dtu.gbar.gitlab.shipment.persistence.dao.ContainerDaoInterface;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Container;
import dk.dtu.gbar.gitlab.shipment.persistence.models.ContainerStatus;

import java.util.List;

public class ContainerService implements ContainerDaoInterface {
    private static ContainerDao containerDao;

    public ContainerService() {
        containerDao = new ContainerDao();
    }

    @Override
    public Container getById(int id) {

        containerDao.openSession();
        Container c = containerDao.getById(id);
        containerDao.closeSession();
        return c;
    }

    @Override
    public void save(Container container) {
        containerDao.openTransaction();
        containerDao.save(container);
        containerDao.closeTransaction();
    }

    @Override
    public List<Container> getAll() {
        containerDao.openSession();
        List<Container> c = containerDao.getAll();
        containerDao.closeSession();
        return c;
    }

    @Override
    public void deleteById(int id) {
        containerDao.openTransaction();
        containerDao.deleteById(id);
        containerDao.closeTransaction();
    }

    @Override
    public void deleteAll() {
        containerDao.openTransaction();
        containerDao.deleteAll();
        containerDao.closeTransaction();
    }

    /**
     *
     * @param container
     * @return
     */
    @Override
    public List<ContainerStatus> getLastStatuses(Container container) {
        containerDao.openSession();
        List<ContainerStatus> s = containerDao.getLastStatuses(container);
        containerDao.closeSession();
        return s;
    }

}
