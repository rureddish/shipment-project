package dk.dtu.gbar.gitlab.shipment.persistence.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "EVENT", schema = "PUBLIC", catalog = "SHIPMENT")
public class Event {
    private int id;
    private int containerFk;
    private int clientFk;
    private int journeyFk;
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

    @Basic
    @Column(name = "CONTAINER_FK", nullable = false)
    public int getContainerFk() {
        return containerFk;
    }

    public void setContainerFk(int containerFk) {
        this.containerFk = containerFk;
    }

    @Basic
    @Column(name = "CLIENT_FK", nullable = false)
    public int getClientFk() {
        return clientFk;
    }

    public void setClientFk(int clientFk) {
        this.clientFk = clientFk;
    }

    @Basic
    @Column(name = "JOURNEY_FK", nullable = false)
    public int getJourneyFk() {
        return journeyFk;
    }

    public void setJourneyFk(int journeyFk) {
        this.journeyFk = journeyFk;
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
                containerFk == that.containerFk &&
                clientFk == that.clientFk &&
                Objects.equals(message, that.message) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, containerFk, clientFk, message, date);
    }

    protected Event() {
    }

    public Event(int containerFk, int clientFk, String message, Date date) {
        this.containerFk = containerFk;
        this.clientFk = clientFk;
        this.message = message;
        this.date = date;
    }
}
