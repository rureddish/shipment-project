package dk.dtu.gbar.gitlab.shipment.persistence.dao;

import dk.dtu.gbar.gitlab.shipment.persistence.models.Client;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Port;

public interface PortDaoInterface  extends CrudInterface<Port> {
    Port getById(int id, boolean eager);
}
