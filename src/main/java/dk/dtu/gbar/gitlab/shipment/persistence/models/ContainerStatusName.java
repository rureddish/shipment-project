package dk.dtu.gbar.gitlab.shipment.persistence.models;

public enum ContainerStatusName {
    TEMPERATURE("Temperature"), PRESSURE("Pressure"), HUMIDITY("Humidity");
    private String statusName;

    ContainerStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }
}
