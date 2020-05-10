package dk.dtu.gbar.gitlab.shipment.persistence.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Journey {
    private Integer id;
    private JourneySailStatus sailStatus;
    private String containerContent;
    private Collection<ContainerStatus> journeyContainerStatusHistory;
    private Collection<Event> journeyEventHistory;
    private Container journeyContainer;
    private Path journeyPath;
    private Client journeyClient;
    private Port journeyOrigin;
    private Port journeyDestination;
    private Port journeyCurrentLocation;
    private Port journeyNextLocation;
    private Collection<Location> journeyTravelHistory;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Enumerated(EnumType.STRING)
    @Column(name = "sail_status", nullable = false, length = 255)
    public JourneySailStatus getSailStatus() {
        return sailStatus;
    }

    public void setSailStatus(JourneySailStatus sailStatus) {
        this.sailStatus = sailStatus;
    }

    @Basic
    @Column(name = "container_content", nullable = false, length = 255)
    public String getContainerContent() {
        return containerContent;
    }

    public void setContainerContent(String containerContent) {
        this.containerContent = containerContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journey journey = (Journey) o;
        return Objects.equals(id, journey.id) &&
                Objects.equals(sailStatus, journey.sailStatus) &&
                Objects.equals(containerContent, journey.containerContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sailStatus, containerContent);
    }

    @OneToMany(mappedBy = "journeyStatusParent",fetch = FetchType.LAZY)
    public Collection<ContainerStatus> getJourneyContainerStatusHistory() {
        return journeyContainerStatusHistory;
    }

    public void setJourneyContainerStatusHistory(Collection<ContainerStatus> containerStatusesById) {
        this.journeyContainerStatusHistory = containerStatusesById;
    }

    @OneToMany(mappedBy = "journeyEventParent", fetch = FetchType.LAZY)
    public Collection<Event> getJourneyEventHistory() {
        return journeyEventHistory;
    }

    public void setJourneyEventHistory(Collection<Event> eventsById) {
        this.journeyEventHistory = eventsById;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "container_fk", referencedColumnName = "id", nullable = true)
    public Container getJourneyContainer() {
        return journeyContainer;
    }

    public void setJourneyContainer(Container containerByContainerFk) {
        this.journeyContainer = containerByContainerFk;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "path_fk", referencedColumnName = "id", nullable = true)
    public Path getJourneyPath() {
        return journeyPath;
    }

    public void setJourneyPath(Path pathByPathFk) {
        this.journeyPath = pathByPathFk;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_fk", referencedColumnName = "id", nullable = false)
    public Client getJourneyClient() {
        return journeyClient;
    }

    public void setJourneyClient(Client clientByClientFk) {
        this.journeyClient = clientByClientFk;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "origin", referencedColumnName = "id", nullable = false)
    public Port getJourneyOrigin() {
        return journeyOrigin;
    }

    public void setJourneyOrigin(Port portByOrigin) {
        this.journeyOrigin = portByOrigin;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destination", referencedColumnName = "id", nullable = false)
    public Port getJourneyDestination() {
        return journeyDestination;
    }

    public void setJourneyDestination(Port portByDestination) {
        this.journeyDestination = portByDestination;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "current_location", referencedColumnName = "id", nullable = false)
    public Port getJourneyCurrentLocation() {
        return journeyCurrentLocation;
    }

    public void setJourneyCurrentLocation(Port portByCurrentLocation) {
        this.journeyCurrentLocation = portByCurrentLocation;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "next_location", referencedColumnName = "id")
    public Port getJourneyNextLocation() {
        return journeyNextLocation;
    }

    public void setJourneyNextLocation(Port portByNextLocation) {
        this.journeyNextLocation = portByNextLocation;
    }

    @OneToMany(mappedBy = "locationJourneyParent",fetch = FetchType.LAZY)
    public Collection<Location> getJourneyTravelHistory() {
        return journeyTravelHistory;
    }

    public void setJourneyTravelHistory(Collection<Location> locationsById) {
        this.journeyTravelHistory = locationsById;
    }

    protected Journey(){
    }

    public Journey(JourneySailStatus sailStatus, String containerContent, Container journeyContainer, Path journeyPath, Client journeyClient, Port journeyOrigin, Port journeyDestination, Port journeyCurrentLocation, Port journeyNextLocation) {
        this.sailStatus = sailStatus;
        this.containerContent = containerContent;
        this.journeyContainer = journeyContainer;
        this.journeyPath = journeyPath;
        this.journeyClient = journeyClient;
        this.journeyOrigin = journeyOrigin;
        this.journeyDestination = journeyDestination;
        this.journeyCurrentLocation = journeyCurrentLocation;
        this.journeyNextLocation = journeyNextLocation;
    }

    public Journey(String containerContent, Container journeyContainer, Path journeyPath, Client journeyClient, Port journeyOrigin, Port journeyDestination, Port journeyCurrentLocation, Port journeyNextLocation) {
        this.containerContent = containerContent;
        this.journeyContainer = journeyContainer;
        this.journeyPath = journeyPath;
        this.journeyClient = journeyClient;
        this.journeyOrigin = journeyOrigin;
        this.journeyDestination = journeyDestination;
        this.journeyCurrentLocation = journeyCurrentLocation;
        this.journeyNextLocation = journeyNextLocation;
        this.sailStatus = JourneySailStatus.PREPARING;
    }
}
