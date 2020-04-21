package dk.dtu.gbar.gitlab.shipment;

import java.util.*;

public class EntityList<V extends Entity> {
    protected ArrayList<V> list;
    protected Integer idNumber = 0;

    public EntityList() {
        list = new ArrayList<>();
    }

    // add entities and assign id
    public void add(V object) {
        if (!list.contains(object)){
            idNumber++;
            object.setID(idNumber);
            list.add(object);
        }
        else { 
            System.out.println("already registered");
        }
    }



    public void remove(V entity) {
        list.remove(entity);
    }

    public ArrayList<V> getList() {
        return list;
    }
}

