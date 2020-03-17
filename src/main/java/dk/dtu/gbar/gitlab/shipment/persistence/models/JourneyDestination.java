package dk.dtu.gbar.gitlab.shipment.persistence.models;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "JOURNEY_DESTINATION", schema = "PUBLIC", catalog = "SHIPMENT")
public class JourneyDestination {
    private int id;
    private Integer journeyFk;
    private Integer previousFk;
    private Integer nextFk;
    private Integer destinationFk;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "JOURNEY_FK", nullable = true)
    public Integer getJourneyFk() {
        return journeyFk;
    }

    public void setJourneyFk(Integer journeyFk) {
        this.journeyFk = journeyFk;
    }

    @Basic
    @Column(name = "PREVIOUS_FK", nullable = true)
    public Integer getPreviousFk() {
        return previousFk;
    }

    public void setPreviousFk(Integer previousFk) {
        this.previousFk = previousFk;
    }

    @Basic
    @Column(name = "NEXT_FK", nullable = true)
    public Integer getNextFk() {
        return nextFk;
    }

    public void setNextFk(Integer nextFk) {
        this.nextFk = nextFk;
    }

    @Basic
    @Column(name = "DESTINATION_FK", nullable = true)
    public Integer getDestinationFk() {
        return destinationFk;
    }

    public void setDestinationFk(Integer destinationFk) {
        this.destinationFk = destinationFk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JourneyDestination that = (JourneyDestination) o;
        return id == that.id &&
                Objects.equals(journeyFk, that.journeyFk) &&
                Objects.equals(previousFk, that.previousFk) &&
                Objects.equals(nextFk, that.nextFk) &&
                Objects.equals(destinationFk, that.destinationFk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, journeyFk, previousFk, nextFk, destinationFk);
    }

    protected JourneyDestination() {
    }

    public JourneyDestination(Integer journeyFk, Integer previousFk, Integer nextFk, Integer destinationFk) {
        this.journeyFk = journeyFk;
        this.previousFk = previousFk;
        this.nextFk = nextFk;
        this.destinationFk = destinationFk;
    }
}

