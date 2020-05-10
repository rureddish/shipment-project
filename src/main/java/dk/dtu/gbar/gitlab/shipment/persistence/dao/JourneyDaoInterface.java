package dk.dtu.gbar.gitlab.shipment.persistence.dao;

import dk.dtu.gbar.gitlab.shipment.persistence.models.Journey;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Port;

public interface JourneyDaoInterface extends CrudInterface<Journey> {
    Journey getById(int id, boolean eager);
}
