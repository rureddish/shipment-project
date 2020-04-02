package dk.dtu.gbar.gitlab.shipment;

import java.util.HashMap;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class List<V extends Entity> {
    public List() {}
    protected HashMap<Integer,V> list;
    protected int idNumber = 0;

    public void add(V object) {
        idNumber++;
        object.setID(idNumber);
        list.put(idNumber, object);
    }

    public HashMap<Integer, V> getList() {
        return list;
    }

    public void setList(HashMap<Integer, V> list) {
        this.list = list;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public void remove(Entity entity) {
        list.remove(entity.getID());
    }
}
