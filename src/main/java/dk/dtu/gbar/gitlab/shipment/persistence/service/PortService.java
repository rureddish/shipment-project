package dk.dtu.gbar.gitlab.shipment.persistence.service;

import dk.dtu.gbar.gitlab.shipment.persistence.dao.ClientDao;
import dk.dtu.gbar.gitlab.shipment.persistence.dao.PortDao;
import dk.dtu.gbar.gitlab.shipment.persistence.dao.PortDaoInterface;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Client;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Port;
import dk.dtu.gbar.gitlab.shipment.persistence.search.SearchCriteria;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.transaction.Transactional;
import java.util.List;

public class PortService implements PortDaoInterface {
    private static PortDao portDao;

    public PortService() {
        portDao = new PortDao();
    }

    @Override
    public Port getById(int id) {
        portDao.openSession();
        Port port = portDao.getById(id);
        portDao.closeSession();
        return port;
    }
    @Override
    @Transactional
    @Fetch(FetchMode.JOIN)
    public Port getById(int id, boolean children) {
        if (children) {
            portDao.openTransaction();
            Port p = portDao.getById(id, true);
            portDao.closeTransaction();
            return p;
        } else {
            return getById(id);
        }
    }

    @Override
    public void save(Port port) {
        portDao.openTransaction();
        portDao.save(port);
        portDao.closeTransaction();
    }

    @Override
    public List<Port> getAll() {
        portDao.openSession();
        List<Port> ports = portDao.getAll();
        portDao.closeSession();
        return ports;
    }

    @Override
    public void deleteById(int id) {
        portDao.openTransaction();
        portDao.deleteById(id);
        portDao.closeTransaction();
    }

    @Override
    public void deleteAll() {
        portDao.openSession();
        portDao.deleteAll();
        portDao.openSession();
    }

    public List<Port> search(SearchCriteria search) {
        portDao.openSession();
        List<Port> ports = portDao.search(search);
        portDao.closeSession();
        return ports;
    }
}

