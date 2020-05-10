package dk.dtu.gbar.gitlab.shipment.persistence.service;

import dk.dtu.gbar.gitlab.shipment.persistence.dao.ShipDao;
import dk.dtu.gbar.gitlab.shipment.persistence.dao.ShipDaoInterface;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Ship;
import dk.dtu.gbar.gitlab.shipment.persistence.search.SearchCriteria;

import java.util.List;


public class ShipService implements ShipDaoInterface {
    private static ShipDao shipDao;

    public ShipService() {
        shipDao = new ShipDao();
    }

    @Override
    public Ship getById(int id) {
        shipDao.openSession();
        Ship ship =shipDao.getById(id);
        shipDao.closeSession();
        return ship;
    }

    @Override
    public void save(Ship ship) {
        shipDao.openTransaction();
        shipDao.save(ship);
        shipDao.closeTransaction();

    }

    @Override
    public List<Ship> getAll() {
        shipDao.openSession();
        List<Ship> ships = shipDao.getAll();
        shipDao.closeSession();
        return ships;
    }

    @Override
    public void deleteById(int id) {
        shipDao.openTransaction();
        shipDao.deleteById(id);
        shipDao.closeTransaction();
    }

    @Override
    public void deleteAll() {
        shipDao.openSession();
        shipDao.deleteAll();
        shipDao.openSession();

    }
    public List<Ship> search(SearchCriteria search) {
        shipDao.openSession();
        List<Ship> ports = shipDao.search(search);
        shipDao.closeSession();
        return ports;
    }
}
