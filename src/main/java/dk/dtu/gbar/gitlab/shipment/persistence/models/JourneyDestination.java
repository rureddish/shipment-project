package dk.dtu.gbar.gitlab.shipment.persistence.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "JOURNEY_DESTINATION", schema = "PUBLIC", catalog = "SHIPMENT")
public class JourneyDestination {
    private int id;
    private Journey journey; // journey FK
    private Destination destination; //destination FK
    private Destination next; //next destination FK
    private Destination previous; //prev destination FK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOURNEY_FK", referencedColumnName = "ID")
    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DESTINATION_FK", referencedColumnName = "ID")
    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PREVIOUS_FK", referencedColumnName = "ID")
    public Destination getNext() {
        return next;
    }

    public void setNext(Destination next) {
        this.next = next;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NEXT_FK", referencedColumnName = "ID")
    public Destination getPrevious() {
        return previous;
    }

    public void setPrevious(Destination previous) {
        this.previous = previous;
    }

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JourneyDestination that = (JourneyDestination) o;
        return id == that.id &&
                Objects.equals(journey, that.journey) &&
                Objects.equals(previous, that.previous) &&
                Objects.equals(next, that.next) &&
                Objects.equals(destination, that.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, journey, previous, next, destination);
    }

    protected JourneyDestination() {
    }

    public JourneyDestination(Journey journey, Destination destination, Destination next, Destination previous) {
        this.journey = journey;
        this.destination = destination;
        this.next = next;
        this.previous = previous;
    }
}


