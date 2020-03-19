package dk.dtu.gbar.gitlab.shipment.persistence.dao;

import dk.dtu.gbar.gitlab.shipment.persistence.models.Container;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Status;

import java.util.List;

public interface ContainerDaoInterface extends CrudInterface<Container> {
    List<Status> getLastStatuses(Container container);
}
