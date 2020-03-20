package dk.dtu.gbar.gitlab.shipment.persistence.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Location {
    private Integer id;
    private Timestamp date;
    private Journey locationJourneyParent; //journey this location mark belongs to
    private Port locationPort; //port that was visited

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
        Location location = (Location) o;
        return Objects.equals(id, location.id) &&
                Objects.equals(date, location.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "journey_fk", referencedColumnName = "id", nullable = false)
    public Journey getLocationJourneyParent() {
        return locationJourneyParent;
    }

    public void setLocationJourneyParent(Journey journeyByJourneyFk) {
        this.locationJourneyParent = journeyByJourneyFk;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "port_fk", referencedColumnName = "id", nullable = false)
    public Port getLocationPort() {
        return locationPort;
    }

    public void setLocationPort(Port portByPortFk) {
        this.locationPort = portByPortFk;
    }
}
