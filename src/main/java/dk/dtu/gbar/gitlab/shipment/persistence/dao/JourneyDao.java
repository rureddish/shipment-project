package dk.dtu.gbar.gitlab.shipment.persistence.dao;

import dk.dtu.gbar.gitlab.shipment.persistence.Connection;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Journey;

import java.util.List;

public class JourneyDao extends Connection implements JourneyDaoInterface {
    @Override
    public Journey getById(int id) {
        return getSession().get(Journey.class,id);
    }

    @Override
    public void save(Journey journey) {

    }

    @Override
    public List<Journey> getAll() {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
