package dk.dtu.gbar.gitlab.shipment;


public class Container {
    private Journey journey;
    private String containerID;
    private String content;
    private String ownerID;

    private ContainerStatus status;

    public Container() {
        ownerID = "";
    }

    // getters and setters
    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getContainerID() {
        return containerID;
    }

    public void setContainerID(String containerID) {
        this.containerID = containerID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

