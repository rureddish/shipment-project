package dk.dtu.gbar.gitlab.shipment.persistence.models;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Immutable
@Table(name = "STATUS", schema = "PUBLIC", catalog = "SHIPMENT")
public class Status {
    private int id;
    private String statusName;
    private String statusValue;
    private Timestamp date;
    private Client client; //client FK
    private Container container; //container FK
    private Journey journey; //Journey FK


    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_FK", referencedColumnName = "ID")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTAINER_FK", referencedColumnName = "ID")
    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOURNEY_FK", referencedColumnName = "ID")
    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    @Basic
    @Column(name = "STATUS_NAME", nullable = false, length = 255)
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Basic
    @Column(name = "STATUS_VALUE", nullable = false, length = 255)
    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    @Basic
    @Column(name = "DATE", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status that = (Status) o;
        return id == that.id &&
                Objects.equals(client, that.client) &&
                Objects.equals(container, that.container) &&
                Objects.equals(statusName, that.statusName) &&
                Objects.equals(statusValue, that.statusValue) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, container, client, statusName, statusValue, date);
    }

    protected Status() {
    }

    public Status(String statusName, String statusValue, Client client, Container container, Journey journey) {
        this.statusName = statusName;
        this.statusValue = statusValue;
        this.client = client;
        this.container = container;
        this.journey = journey;
    }
}
