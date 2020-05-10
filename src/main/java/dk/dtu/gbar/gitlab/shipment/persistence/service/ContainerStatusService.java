package dk.dtu.gbar.gitlab.shipment.persistence.service;

import dk.dtu.gbar.gitlab.shipment.persistence.dao.ContainerStatusDao;
import dk.dtu.gbar.gitlab.shipment.persistence.dao.ContainerStatusDaoInterface;
import dk.dtu.gbar.gitlab.shipment.persistence.models.ContainerStatus;

import java.util.List;

public class ContainerStatusService implements ContainerStatusDaoInterface {
    private static ContainerStatusDao containerStatusDao;

    public ContainerStatusService() {
        containerStatusDao = new ContainerStatusDao();
    }

    @Override
    public ContainerStatus getById(int id) {
        containerStatusDao.openSession();
        ContainerStatus cs = containerStatusDao.getById(id);
        containerStatusDao.closeSession();
        return cs;

    }

    @Override
    public void save(ContainerStatus containerStatus) {
        containerStatusDao.openTransaction();
        containerStatusDao.save(containerStatus);
        containerStatusDao.closeTransaction();
    }

    @Override
    public List<ContainerStatus> getAll() {
        containerStatusDao.openSession();
        List<ContainerStatus> containerStatuses = containerStatusDao.getAll();
        containerStatusDao.closeSession();
        return containerStatuses;
    }

    @Override
    public void deleteById(int id) {
        containerStatusDao.openTransaction();
        containerStatusDao.deleteById(id);
        containerStatusDao.closeTransaction();
    }

    @Override
    public void deleteAll() {
        containerStatusDao.openTransaction();
        containerStatusDao.deleteAll();
        containerStatusDao.closeTransaction();
    }
}
