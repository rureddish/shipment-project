package dk.dtu.gbar.gitlab.shipment.persistence.service;

import dk.dtu.gbar.gitlab.shipment.persistence.dao.JourneyDao;
import dk.dtu.gbar.gitlab.shipment.persistence.dao.JourneyDaoInterface;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Journey;
import dk.dtu.gbar.gitlab.shipment.persistence.models.JourneySailStatus;
import dk.dtu.gbar.gitlab.shipment.persistence.search.SearchCriteria;
import org.hibernate.Filter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.transaction.Transactional;
import java.util.List;

public class JourneyService implements JourneyDaoInterface {
    private static JourneyDao journeyDao;

    public JourneyService() {
        journeyDao = new JourneyDao();
    }

    @Override
    public Journey getById(int id) {
        journeyDao.openSession();
        Journey journey = journeyDao.getById(id);
        journeyDao.closeSession();
        return journey;
    }

    @Override
    @Transactional
    @Fetch(FetchMode.JOIN)
    public Journey getById(int id, boolean children) {
        if (children) {
            journeyDao.openTransaction();
            Journey j = journeyDao.getById(id, true);
            journeyDao.closeTransaction();
            return j;
        } else {
            return getById(id);
        }
    }

    @Override
    public void save(Journey port) {
        journeyDao.openTransaction();
        journeyDao.save(port);
        journeyDao.closeTransaction();
    }

    @Override
    public List<Journey> getAll() {
        journeyDao.openSession();
        List<Journey> journeys = journeyDao.getAll();
        journeyDao.closeSession();
        return journeys;
    }

    @Override
    public void deleteById(int id) {
        journeyDao.openTransaction();
        journeyDao.deleteById(id);
        journeyDao.closeTransaction();
    }

    @Override
    public void deleteAll() {
        journeyDao.openSession();
        journeyDao.deleteAll();
        journeyDao.openSession();
    }

    public List<Journey> search(SearchCriteria search) {
        journeyDao.openSession();
        List<Journey> journeys = journeyDao.search(search);
        journeyDao.closeSession();
        return journeys;
    }
    public List<Journey> getFiltered(JourneySailStatus status){
        journeyDao.openSession();
        Filter filter = journeyDao.getSession().enableFilter("statusFilter");
        filter.setParameter("status",status);
        journeyDao.getSession().beginTransaction();
        List<Journey> journeys = journeyDao.getAll();
        journeyDao.closeTransaction();
        return journeys;

    }
}

