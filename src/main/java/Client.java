public class Client {
    String Name;
    String Address;
    String RefPerson;
    String Email;

    public Client(String Name, String Address, String RefPerson, String Email){}

    public void setName(String name) {
        Name = name;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setRefPerson(String refPerson) {
        RefPerson = refPerson;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getRefPerson() {
        return RefPerson;
    }

    public String getEmail() {
        return Email;
    }
}
