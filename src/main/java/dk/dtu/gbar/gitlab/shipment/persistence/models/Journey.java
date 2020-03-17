package dk.dtu.gbar.gitlab.shipment.persistence.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "JOURNEY", schema = "PUBLIC", catalog = "SHIPMENT")
public class Journey {
    private int id;
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journey that = (Journey) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    protected Journey() {
    }

    public Journey(String name) {
        this.name = name;
    }
}
