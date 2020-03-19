package dk.dtu.gbar.gitlab.shipment.persistence.models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "CONTAINER", schema = "PUBLIC", catalog = "SHIPMENT")
public class Container {
    private int id;
    private String name;
    private Client client; // Client fk
    private Journey journey; //Journey fk
    private Set<Status> allStatuses;
    private Set<Status> currentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_FK", referencedColumnName = "ID")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @OneToMany(mappedBy = "container",fetch = FetchType.LAZY)
    public Set<Status> getAllStatuses() {
        return allStatuses;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOURNEY_FK", referencedColumnName = "ID")
    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public void setAllStatuses(Set<Status> allStatuses) {
        this.allStatuses = allStatuses;
    }

    @OneToMany(mappedBy = "journey")
    public Set<Status> getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Set<Status> currentStatus) {
        this.currentStatus = currentStatus;
    }

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Container that = (Container) o;
        return id == that.id &&
                Objects.equals(client, that.client) &&
                Objects.equals(name, that.name) &&
                Objects.equals(journey, that.journey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, name, journey);
    }

    protected Container() {
    }

    public Container(Client client, String name, Journey journey) {
        this.client = client;
        this.name = name;
        this.journey = journey;
    }

    public Container(String name) {
        this.name = name;
        this.journey = null;
        this.client = null;
    }


}
