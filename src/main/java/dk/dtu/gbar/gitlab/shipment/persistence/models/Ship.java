package dk.dtu.gbar.gitlab.shipment.persistence.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Ship {
    private Integer id;
    private String name;
    private Collection<Container> shipContainers;
    private Path shipPath;
    private Port shipPort;
    private PathPort currentNode;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    @OneToMany(mappedBy = "containerShip")
    public Collection<Container> getShipContainers() {
        return shipContainers;
    }

    public void setShipContainers(Collection<Container> shipContainers) {
        this.shipContainers = shipContainers;
    }

    @ManyToOne()
    @JoinColumn(name = "path_fk", referencedColumnName = "id")
    public Path getShipPath() {
        return shipPath;
    }

    public void setShipPath(Path shipPath) {
        this.shipPath = shipPath;
    }

    @ManyToOne()
    @JoinColumn(name = "port_fk", referencedColumnName = "id")
    public Port getShipPort() {
        return shipPort;
    }

    public void setShipPort(Port shipPort) {
        this.shipPort = shipPort;
    }

    @ManyToOne()
    @JoinColumn(name = "current_node", referencedColumnName = "id")
    public PathPort getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(PathPort currentNode) {
        this.currentNode = currentNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return Objects.equals(id, ship.id) &&
                Objects.equals(name, ship.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    protected Ship() {
    }

    public Ship(String name) {
        this.name = name;
        this.shipPath = null;
        this.shipPort = null;
    }

    public Ship(String name, Port port) {
        this.name = name;
        this.shipPort = port;
    }
}
