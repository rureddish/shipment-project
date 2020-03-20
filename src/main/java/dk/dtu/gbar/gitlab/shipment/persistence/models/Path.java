package dk.dtu.gbar.gitlab.shipment.persistence.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Path {
    private Integer id;
    private String name;
    private Collection<Journey> journeyUsers; //journeys that used or are actively using this path
    private Collection<PathPort> seaPath; //sea path the ships will sail trough

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
    @Column(name = "name", nullable = false, length = 255)
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
        Path path = (Path) o;
        return Objects.equals(id, path.id) &&
                Objects.equals(name, path.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "journeyPath",fetch = FetchType.LAZY)
    public Collection<Journey> getJourneyUsers() {
        return journeyUsers;
    }

    public void setJourneyUsers(Collection<Journey> journeysById) {
        this.journeyUsers = journeysById;
    }

    @OneToMany(mappedBy = "pathParent",fetch = FetchType.EAGER)
    public Collection<PathPort> getSeaPath() {
        return seaPath;
    }

    public void setSeaPath(Collection<PathPort> pathPortsById) {
        this.seaPath = pathPortsById;
    }

    protected Path(){
    }

    public Path(String name) {
        this.name = name;
    }
}
