package dk.dtu.gbar.gitlab.shipment.persistence.models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "JOURNEY", schema = "PUBLIC", catalog = "SHIPMENT")
public class Journey {
    private int id;
    private String name;
    @ManyToMany
    @JoinTable(name = "JOURNEY_DESTINATION",
            joinColumns = @JoinColumn(name = "JOURNEY_FK", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "DESTINATION_FK", referencedColumnName = "ID"))
    private Set<Destination> path;

    public Set<Destination> getPath() {
        return path;
    }

    public void setPath(Set<Destination> path) {
        this.path = path;
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
