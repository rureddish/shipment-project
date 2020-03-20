package dk.dtu.gbar.gitlab.shipment.persistence.service;

import dk.dtu.gbar.gitlab.shipment.persistence.dao.ClientDao;
import dk.dtu.gbar.gitlab.shipment.persistence.dao.ClientInterface;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Client;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.transaction.Transactional;
import java.util.List;

public class ClientService implements ClientInterface {
    private static ClientDao clientDao;

    public ClientService() {
        clientDao = new ClientDao();
    }

    /**
     * @param id id of the client
     * @return Client with this id
     */
    @Override
    public Client getById(int id) {
        clientDao.openSession();
        Client c = clientDao.getById(id);
        clientDao.closeSession();
        return c;
    }

    /**
     * @param id       id of the client
     * @param children whether its other relations should be loaded too
     * @return Client with this id
     */
    @Override
    @Transactional
    @Fetch(FetchMode.JOIN)
    public Client getById(int id, boolean children) {
        if (children) {
            clientDao.openTransaction();
            Client c = clientDao.getById(id, true);
            clientDao.closeTransaction();
            return c;
        } else {
            return getById(id);
        }
    }

    @Override
    public void save(Client client) {
        clientDao.openTransaction();
        clientDao.save(client);
        clientDao.closeTransaction();
    }

    /**
     * Returns all clients WITHOUT their other relations
     *
     * @return All clients
     */
    @Override
    public List<Client> getAll() {
        clientDao.openSession();
        List<Client> clients = clientDao.getAll();
        clientDao.closeSession();
        return clients;
    }

    @Override
    public void deleteById(int id) {
        clientDao.openTransaction();
        clientDao.deleteById(id);
        clientDao.closeTransaction();
    }

    @Override
    public void deleteAll() {
        clientDao.openSession();
        clientDao.deleteAll();
        clientDao.closeSession();
    }

    public ClientDao getClientDao() {
        return clientDao;
    }
}
