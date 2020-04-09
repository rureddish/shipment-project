package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EntityList<V extends Entity> {
    protected ArrayList<V> list;
    protected Integer idNumber = 0;

    public EntityList() {
        list = new ArrayList<>();
    }

    // search list by predicates
    @SafeVarargs
    public final ArrayList<V> search(Predicate<V>... SearchPredicates){
        ArrayList<Predicate<V>> predicates = new ArrayList<>(Arrays.asList((SearchPredicates)));
        return (ArrayList<V>) list.stream()
                .filter(predicates.stream().reduce(x-> false, Predicate::or))
                .collect(Collectors.toList());
    }

    public List<V> filterBy(List<V> list, Predicate<V> predicate){
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    // add entities and assign id
    public void add(V object) {
        if(!list.contains(object)){
            idNumber++;
            object.setID(idNumber);
            list.add(object);
        }
        else{
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

