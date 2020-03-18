package dk.dtu.gbar.gitlab.shipment.persistence.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "STATUS", schema = "PUBLIC", catalog = "SHIPMENT")
public class Status {
    private int id;
    private int containerFk;
    private int clientFk;
    private int journeyFk;
    private String statusName;
    private String statusValue;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "CLIENT_FK", referencedColumnName = "ID")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "CONTAINER_FK", referencedColumnName = "ID")
    private Container container;

    @ManyToOne
    @JoinColumn(name = "JOURNEY_FK",referencedColumnName = "ID")
    private Journey journey;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
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
    @Column(name = "JOURNEY_FK",nullable = false)
    public int getJourneyFk() {
        return journeyFk;
    }

    public void setJourneyFk(int journeyFk) {
        this.journeyFk = journeyFk;
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
        Status that = (Status) o;
        return id == that.id &&
                containerFk == that.containerFk &&
                clientFk == that.clientFk &&
                Objects.equals(statusName, that.statusName) &&
                Objects.equals(statusValue, that.statusValue) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, containerFk, clientFk, statusName, statusValue, date);
    }

    protected Status() {
    }

    public Status(int containerFk, int clientFk, String statusName, String statusValue, Date date) {
        this.containerFk = containerFk;
        this.clientFk = clientFk;
        this.statusName = statusName;
        this.statusValue = statusValue;
        this.date = date;
    }
}
