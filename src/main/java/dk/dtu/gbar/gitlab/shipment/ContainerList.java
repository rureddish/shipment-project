package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ContainerList<list> extends List {
    private ArrayList<Container> list;

    public ResponseObject searchContainer(String criterium) {
        ArrayList<Container> containers = list.stream().filter
                (c -> c.getOwnerID().contains(criterium)
                   || c.getContent().contains(criterium))
                .collect(Collectors.toCollection(ArrayList::new));
        if (containers.size()>0){
            String errorMessage = containers.get(0).getContainerID();
            return new ResponseObject(0, errorMessage);
        }
        else {
            return new ResponseObject(0,"No containers found");
        }
    }

    public ResponseObject addContainer (Container c)  {
        if (searchContainer(c.getContainerID()).getErrorMessage().equals(c.getContainerID())){
            ResponseObject response = new ResponseObject(0, "Container with this id already exists");
            System.out.println("Container could not be registered, a container with this id already exists");
            return response;
        }
        else if (c.getOwnerID().isEmpty()||c.getContent().isEmpty()){
            ResponseObject response = new ResponseObject(0, "Container info missing");
            System.out.println("Container could not be registered, info is missing");
            return response;
        }
        else{
            list.add(c);
            ResponseObject response = new ResponseObject(0, "Container added");
            return response;
        }
    }

    public ResponseObject remove(Container container) {
        list.remove(container);
        System.out.println("client has been removed");
        ResponseObject response = new ResponseObject(0, "client removed");
        return response;
    }

    public ArrayList<Container> getList() {
        return list;
    }

    public void setList(ArrayList<Container> list) {
        this.list = list;
    }


}
