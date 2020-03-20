package dk.dtu.gbar.gitlab.shipment.persistence.dao;

import dk.dtu.gbar.gitlab.shipment.persistence.models.Client;

public interface ClientInterface extends CrudInterface<Client> {
    Client getById(int id, boolean eager);
}
