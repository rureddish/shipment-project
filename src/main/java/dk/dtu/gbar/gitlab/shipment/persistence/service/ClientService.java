package dk.dtu.gbar.gitlab.shipment.persistence.service;

import dk.dtu.gbar.gitlab.shipment.persistence.dao.ClientDao;
import dk.dtu.gbar.gitlab.shipment.persistence.dao.ClientDaoInterface;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Client;

import java.util.List;

public class ClientService implements ClientDaoInterface {
    private ClientDao clientDao;
    public ClientService() {
        clientDao = new ClientDao();
    }

    @Override
    public Client getById(int id) {
        clientDao.openSession();
        Client c = clientDao.getById(id);
        clientDao.closeSession();
        return c;
    }

    @Override
    public void save(Client client) {
        clientDao.openTransaction();
        clientDao.save(client);
        clientDao.closeTransaction();
    }

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
