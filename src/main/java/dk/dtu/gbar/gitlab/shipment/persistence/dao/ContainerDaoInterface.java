package dk.dtu.gbar.gitlab.shipment.persistence.dao;

import dk.dtu.gbar.gitlab.shipment.persistence.models.Container;
import dk.dtu.gbar.gitlab.shipment.persistence.models.ContainerStatus;

import java.util.List;

public interface ContainerDaoInterface extends CrudInterface<Container> {
    List<ContainerStatus> getLastStatuses(Container container);
}
