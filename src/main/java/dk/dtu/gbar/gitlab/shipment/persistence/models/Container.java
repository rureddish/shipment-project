package dk.dtu.gbar.gitlab.shipment.persistence.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "container")
public class Container {
    private Integer id;
    private String name;
    private Boolean onJourney;
    private Client containerOwner;
    private Collection<Journey> containerJourneys;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "on_journey", nullable = false)
    public Boolean getOnJourney() {
        return onJourney;
    }

    public void setOnJourney(Boolean onJourney) {
        this.onJourney = onJourney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Container container = (Container) o;
        return Objects.equals(id, container.id) &&
                Objects.equals(name, container.name) &&
                Objects.equals(onJourney, container.onJourney);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, onJourney);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_fk", referencedColumnName = "id")
    public Client getContainerOwner() {
        return containerOwner;
    }

    public void setContainerOwner(Client clientByClientFk) {
        this.containerOwner = clientByClientFk;
    }

    @OneToMany(mappedBy = "journeyContainer", fetch = FetchType.LAZY)
    public Collection<Journey> getContainerJourneys() {
        return containerJourneys;
    }

    public void setContainerJourneys(Collection<Journey> journeysById) {
        this.containerJourneys = journeysById;
    }

    protected Container() {
    }

    public Container(String name, Boolean onJourney, Client containerOwner) {
        this.name = name;
        this.onJourney = onJourney;
        this.containerOwner = containerOwner;
    }

    public Container(String name, Client containerOwner) {
        this(name, false, containerOwner);
    }

    public Container(String name) {
        this(name, false, null);
    }
}
