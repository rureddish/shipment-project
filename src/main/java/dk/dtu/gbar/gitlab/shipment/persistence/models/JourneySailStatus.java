package dk.dtu.gbar.gitlab.shipment.persistence.models;

public enum JourneySailStatus {
    PREPARING("Preparing"), IN_DOCK("In dock"), SAILING("Sailing"), FINISHED("Finished");
    private String status;

    JourneySailStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
