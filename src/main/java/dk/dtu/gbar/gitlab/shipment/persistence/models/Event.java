package dk.dtu.gbar.gitlab.shipment.persistence.models;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Immutable
@Table(name = "EVENT", schema = "PUBLIC", catalog = "SHIPMENT")
public class Event {
    private int id;
    private Container container; // container FK
    private Client client; //client FK
    private Journey journey; // journey FK
    private String message;
    private Date date;

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
    @JoinColumn(name = "CONTAINER_FK", referencedColumnName = "ID")
    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
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
    @JoinColumn(name = "JOURNEY_FK", referencedColumnName = "ID")
    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    @Basic
    @Column(name = "MESSAGE", nullable = false, length = 1000)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "DATE", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event that = (Event) o;
        return id == that.id &&
                Objects.equals(container, that.container) &&
                Objects.equals(client, that.client) &&
                Objects.equals(journey, that.journey) &&
                Objects.equals(message, that.message) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, container, client, journey, message, date);
    }

    protected Event() {
    }

    public Event(Container container, Client client, Journey journey, String message, Date date) {
        this.container = container;
        this.client = client;
        this.journey = journey;
        this.message = message;
        this.date = date;
    }
}
