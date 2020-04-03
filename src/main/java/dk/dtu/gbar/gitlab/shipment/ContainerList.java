package dk.dtu.gbar.gitlab.shipment;

import java.util.List;
import java.util.function.Predicate;

public class ContainerList extends EntityList<Container> {
    public ContainerList() {
        super();
    }


    //search all relevant fields by string
    public List<Container> searchByString(String string){
        return search(locationContains(string));
    }

    // search predicates
    public Predicate<Container> locationContains(String string) {
        return (str -> str.getLocation().getPlaceName().contains(string));
    }

}
