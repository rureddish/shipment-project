package dk.dtu.gbar.gitlab.shipment.persistence.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "client")
public class Client {
    private Integer id;
    private String clientName;
    private String referencePerson;
    private String email;
    private String address;
    private String password;
    private ClientStatus clientStatus;
    private Collection<Container> clientsContainers;
    private Collection<Journey> clientsJourneys;

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
    @Column(name = "CLIENT_NAME", nullable = false, length = 255)
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Basic
    @Column(name = "reference_person", nullable = false, length = 255)
    public String getReferencePerson() {
        return referencePerson;
    }

    public void setReferencePerson(String referencePerson) {
        this.referencePerson = referencePerson;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    public ClientStatus getClientStatus() {
        return clientStatus;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 60)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setClientStatus(ClientStatus clientStatus) {
        this.clientStatus = clientStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) &&
                Objects.equals(clientName, client.clientName) &&
                Objects.equals(referencePerson, client.referencePerson) &&
                Objects.equals(email, client.email) &&
                Objects.equals(clientStatus, client.clientStatus) &&
                Objects.equals(address, client.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientName, referencePerson, email, address, clientStatus);
    }

    @OneToMany(mappedBy = "containerOwner", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    public Collection<Container> getClientsContainers() {
        return clientsContainers;
    }

    public void setClientsContainers(Collection<Container> containersById) {
        this.clientsContainers = containersById;
    }

    @OneToMany(mappedBy = "journeyClient", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    public Collection<Journey> getClientsJourneys() {
        return clientsJourneys;
    }

    public void setClientsJourneys(Collection<Journey> journeysById) {
        this.clientsJourneys = journeysById;
    }

    public Client() {
    }

    public Client(String clientName, String address, String referencePerson, String email, String password, ClientStatus clientStatus) {
        this.clientName = clientName;
        this.address = address;
        this.referencePerson = referencePerson;
        this.email = email;
        this.password = password;
        this.clientStatus = clientStatus;
    }

    public Client(String clientName, String address, String referencePerson, String email, String password) {
        this(clientName, address, referencePerson, email, password, ClientStatus.ACTIVE);
    }
}
