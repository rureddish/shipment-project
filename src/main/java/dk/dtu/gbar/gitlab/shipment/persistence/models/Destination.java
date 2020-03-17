package dk.dtu.gbar.gitlab.shipment.persistence.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "DESTINATION", schema = "PUBLIC", catalog = "SHIPMENT")
public class Destination {
    private int id;
    private String name;
    private String coordinates;

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
    @Column(name = "NAME", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "COORDINATES", nullable = true, length = 255)
    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destination that = (Destination) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(coordinates, that.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates);
    }

    protected Destination() {
    }

    public Destination(String name, String coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }
}
