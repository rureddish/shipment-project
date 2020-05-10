package dk.dtu.gbar.gitlab.shipment.persistence.models;

public enum JourneySailStatus {
    PREPARING("PREPARING"), IN_DOCK("IN DOCK"), SAILING("SAILING"), FINISHED("FINISHED");
    private String status;

    JourneySailStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
