package dk.dtu.gbar.gitlab.shipment.persistence.dao;

import dk.dtu.gbar.gitlab.shipment.persistence.models.Client;

import java.util.List;

public interface ClientDaoInterface {
    public abstract Client getById(int id);
    public abstract void save(Client client);
    public abstract List<Client> getAll();
    public abstract void deleteById(int id);
    public abstract void deleteAll();

}
