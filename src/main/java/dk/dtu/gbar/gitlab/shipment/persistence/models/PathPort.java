package dk.dtu.gbar.gitlab.shipment.persistence.models;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "path_port", schema = "PUBLIC", catalog = "SHIPMENT")
public class PathPort {
    private Integer id;
    private Path pathParent; //path this node belongs to
    private Port portParent; //port this node belongs to
    private PathPort previous; //previous node
    private PathPort next; //next node

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PathPort pathPort = (PathPort) o;
        return Objects.equals(id, pathPort.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "path_fk", referencedColumnName = "id", nullable = false)
    public Path getPathParent() {
        return pathParent;
    }

    public void setPathParent(Path pathByPathFk) {
        this.pathParent = pathByPathFk;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "port_fk", referencedColumnName = "id", nullable = false)
    public Port getPortParent() {
        return portParent;
    }

    public void setPortParent(Port portByPortFk) {
        this.portParent = portByPortFk;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "previous", referencedColumnName = "id")
    public PathPort getPrevious() {
        return previous;
    }

    public void setPrevious(PathPort pathPortByPrevious) {
        this.previous = pathPortByPrevious;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "next", referencedColumnName = "id")
    public PathPort getNext() {
        return next;
    }

    public void setNext(PathPort pathPortByNext) {
        this.next = pathPortByNext;
    }

    protected PathPort(){}

    public PathPort(Path pathParent, Port portParent, PathPort previous, PathPort next) {
        this.pathParent = pathParent;
        this.portParent = portParent;
        this.previous = previous;
        this.next = next;
    }

    public PathPort(Path pathParent, Port portParent) {
        this(pathParent, portParent, null, null);
    }
}
