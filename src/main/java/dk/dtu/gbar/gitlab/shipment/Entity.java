package dk.dtu.gbar.gitlab.shipment;

/**
 * Superclass of all entities registered for the shipping company,
 * adds field ID to enumerate.
 */
public class Entity {
private Integer ID;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
}
