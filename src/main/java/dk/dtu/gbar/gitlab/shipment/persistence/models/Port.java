package dk.dtu.gbar.gitlab.shipment.persistence.models;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@SQLDelete(sql = "UPDATE port SET 'status' = 'DELETED' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "status != 'DELETED'")
public class Port {
    private Integer id;
    private String name;
    private String gps;
    private PortStatus status;
    private Collection<Location> visitedList; //ships that visited this port on their journeys
    private Collection<PathPort> pathsGoingTrough; //paths that goes trough this port
    private Collection<Container> portContainers; // containers that are located in this port

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

    @Basic
    @Column(name = "gps", nullable = false, length = 255)
    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 255)
    public PortStatus getStatus() {
        return status;
    }

    public void setStatus(PortStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Port port = (Port) o;
        return Objects.equals(id, port.id) &&
                Objects.equals(name, port.name) &&
                Objects.equals(gps, port.gps) &&
                Objects.equals(status, port.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gps, status);
    }

    @OneToMany(mappedBy = "locationPort")
    public Collection<Location> getVisitedList() {
        return visitedList;
    }

    public void setVisitedList(Collection<Location> locationsById) {
        this.visitedList = locationsById;
    }

    @OneToMany(mappedBy = "portParent")
    public Collection<PathPort> getPathsGoingTrough() {
        return pathsGoingTrough;
    }

    public void setPathsGoingTrough(Collection<PathPort> pathPortsById) {
        this.pathsGoingTrough = pathPortsById;
    }

    @OneToMany(mappedBy = "containerLocation")
    public Collection<Container> getPortContainers() {
        return portContainers;
    }

    public void setPortContainers(Collection<Container> portContainers) {
        this.portContainers = portContainers;
    }

    @PreRemove
    public void removePort() {
        this.status = PortStatus.DELETED;
    }

    protected Port() {
    }

    public Port(String name, String gps, PortStatus status) {
        this.name = name;
        this.gps = gps;
        this.status = status;
    }

    public Port(String name, String gps) {
        this(name, gps, PortStatus.OPEN);
    }
}

