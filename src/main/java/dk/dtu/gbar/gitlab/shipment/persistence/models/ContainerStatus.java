package dk.dtu.gbar.gitlab.shipment.persistence.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "container_status", schema = "public", catalog = "shipment")
public class ContainerStatus {
    private Integer id;
    private ContainerStatusName statusName;
    private String statusValue;
    private Timestamp date;
    private Journey journeyStatusParent; //journey this container status belongs to

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
    @Column(name = "status_name", nullable = false, length = 255)
    public ContainerStatusName getStatusName() {
        return statusName;
    }

    public void setStatusName(ContainerStatusName statusName) {
        this.statusName = statusName;
    }

    @Basic
    @Column(name = "status_value", nullable = false, length = 255)
    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    @Basic
    @Column(name = "date", nullable = false)
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
        ContainerStatus that = (ContainerStatus) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(statusName, that.statusName) &&
                Objects.equals(statusValue, that.statusValue) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, statusName, statusValue, date);
    }

    @ManyToOne()
    @JoinColumn(name = "journey_fk", referencedColumnName = "id", nullable = false)
    public Journey getJourneyStatusParent() {
        return journeyStatusParent;
    }

    public void setJourneyStatusParent(Journey journeyByJourneyFk) {
        this.journeyStatusParent = journeyByJourneyFk;
    }

    protected ContainerStatus(){
    }

    public ContainerStatus(ContainerStatusName statusName, String statusValue, Timestamp date, Journey journeyStatusParent) {
        this.statusName = statusName;
        this.statusValue = statusValue;
        this.date = date;
        this.journeyStatusParent = journeyStatusParent;
    }

    public ContainerStatus(ContainerStatusName statusName, String statusValue, Journey journeyStatusParent) {
        this.statusName = statusName;
        this.statusValue = statusValue;
        this.journeyStatusParent = journeyStatusParent;
        Date date = new Date();
        this.date = new Timestamp(date.getTime());
    }
}
