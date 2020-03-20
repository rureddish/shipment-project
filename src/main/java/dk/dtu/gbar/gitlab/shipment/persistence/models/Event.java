package dk.dtu.gbar.gitlab.shipment.persistence.models;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Immutable
public class Event {
    private Integer id;
    private String eventName;
    private String eventMessage;
    private Timestamp date;
    private Journey journeyEventParent; //journey this event belongs to

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
    @Column(name = "event_name", nullable = false, length = 255)
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Basic
    @Column(name = "event_message", nullable = false, length = 512)
    public String getEventMessage() {
        return eventMessage;
    }

    public void setEventMessage(String eventMessage) {
        this.eventMessage = eventMessage;
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
        Event event = (Event) o;
        return Objects.equals(id, event.id) &&
                Objects.equals(eventName, event.eventName) &&
                Objects.equals(eventMessage, event.eventMessage) &&
                Objects.equals(date, event.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventName, eventMessage, date);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "journey_fk", referencedColumnName = "id", nullable = false)
    public Journey getJourneyEventParent() {
        return journeyEventParent;
    }

    public void setJourneyEventParent(Journey journeyByJourneyFk) {
        this.journeyEventParent = journeyByJourneyFk;
    }

    protected Event(){}

    public Event(String eventName, String eventMessage, Timestamp date, Journey journeyEventParent) {
        this.eventName = eventName;
        this.eventMessage = eventMessage;
        this.date = date;
        this.journeyEventParent = journeyEventParent;
    }

    public Event(String eventName, String eventMessage, Journey journeyEventParent) {
        this.eventName = eventName;
        this.eventMessage = eventMessage;
        this.journeyEventParent = journeyEventParent;
    }
}
