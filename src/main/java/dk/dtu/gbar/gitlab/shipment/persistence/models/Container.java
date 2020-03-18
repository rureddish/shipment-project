package dk.dtu.gbar.gitlab.shipment.persistence.models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "CONTAINER", schema = "PUBLIC", catalog = "SHIPMENT")
public class Container {
    private int id;
    private Integer clientFk;
    private String name;
    private Integer journeyId;

    @ManyToOne
    @JoinColumn(name = "CLIENT_FK", referencedColumnName = "ID")
    private Client client;

    @OneToMany(mappedBy = "container")
    private Set<Status> allStatuses;

    @OneToMany(mappedBy = "journey")
    private Set<Status> currentStatus;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Status> getAllStatuses() {
        return allStatuses;
    }

    public void setAllStatuses(Set<Status> allStatuses) {
        this.allStatuses = allStatuses;
    }

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
    @Column(name = "CLIENT_FK", nullable = true)
    public Integer getClientFk() {
        return clientFk;
    }

    public void setClientFk(Integer clientFk) {
        this.clientFk = clientFk;
    }

    @Basic
    @Column(name = "NAME", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "JOURNEY_ID", nullable = true)
    public Integer getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(Integer journeyId) {
        this.journeyId = journeyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Container that = (Container) o;
        return id == that.id &&
                Objects.equals(clientFk, that.clientFk) &&
                Objects.equals(name, that.name) &&
                Objects.equals(journeyId, that.journeyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientFk, name, journeyId);
    }

    protected Container() {
    }

    public Container(Integer clientFk, String name, Integer journeyId) {
        this.clientFk = clientFk;
        this.name = name;
        this.journeyId = journeyId;
    }


}
