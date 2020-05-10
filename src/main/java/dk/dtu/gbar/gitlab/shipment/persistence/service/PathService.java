package dk.dtu.gbar.gitlab.shipment.persistence.service;

import dk.dtu.gbar.gitlab.shipment.persistence.dao.PathDao;
import dk.dtu.gbar.gitlab.shipment.persistence.dao.PathDaoInterface;
import dk.dtu.gbar.gitlab.shipment.persistence.dao.PortDao;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Path;
import dk.dtu.gbar.gitlab.shipment.persistence.models.PathPort;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Ship;
import dk.dtu.gbar.gitlab.shipment.persistence.search.SearchCriteria;

import java.util.List;

public class PathService implements PathDaoInterface {
    private static PathDao pathDao;

    public PathService() {
        pathDao = new PathDao();
    }

    @Override
    public Path getById(int id) {
        pathDao.openSession();
        Path p = pathDao.getById(id);
        pathDao.closeSession();
        return p;
    }

    @Override
    public void save(Path path) {
        pathDao.openTransaction();
        pathDao.save(path);
        pathDao.closeTransaction();
    }

    @Override
    public List<Path> getAll() {
        pathDao.openSession();
        List<Path> paths = pathDao.getAll();
        pathDao.closeSession();
        return paths;
    }

    @Override
    public void deleteById(int id) {
        pathDao.openTransaction();
        pathDao.deleteById(id);
        pathDao.closeTransaction();
    }

    @Override
    public void deleteAll() {
        pathDao.openTransaction();
        pathDao.deleteAll();
        pathDao.closeTransaction();
    }

    public List<Path> search(SearchCriteria search) {
        pathDao.openSession();
        List<Path> ports = pathDao.search(search);
        pathDao.closeSession();
        return ports;
    }
    public PathPort start(Path path){
        pathDao.openSession();
        PathPort p = pathDao.start(path);
        pathDao.closeSession();
        return p;
    }
}
