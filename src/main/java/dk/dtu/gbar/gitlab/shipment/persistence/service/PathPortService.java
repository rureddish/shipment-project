package dk.dtu.gbar.gitlab.shipment.persistence.service;

import dk.dtu.gbar.gitlab.shipment.persistence.dao.PathPortDao;
import dk.dtu.gbar.gitlab.shipment.persistence.dao.PathPortDaoInterface;
import dk.dtu.gbar.gitlab.shipment.persistence.models.PathPort;

import java.util.List;

public class PathPortService implements PathPortDaoInterface {
    PathPortDao pathPortDao;

    public PathPortService() {
        this.pathPortDao = new PathPortDao();
    }

    @Override
    public PathPort getById(int id) {
        pathPortDao.openSession();
        PathPort pp = pathPortDao.getById(id);
        pathPortDao.closeSession();
        return pp;
    }

    @Override
    public void save(PathPort pathPort) {
        pathPortDao.openTransaction();
        pathPortDao.save(pathPort);
        pathPortDao.closeTransaction();
    }

    @Override
    public List<PathPort> getAll() {
        pathPortDao.openSession();
        List<PathPort> pps = pathPortDao.getAll();
        pathPortDao.closeSession();
        return pps;

    }

    @Override
    public void deleteById(int id) {
        pathPortDao.openTransaction();
        pathPortDao.deleteById(id);
        pathPortDao.closeTransaction();
    }

    @Override
    public void deleteAll() {
        pathPortDao.openTransaction();
        pathPortDao.deleteAll();
        pathPortDao.closeTransaction();
    }
}
