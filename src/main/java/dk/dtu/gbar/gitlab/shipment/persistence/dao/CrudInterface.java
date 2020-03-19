package dk.dtu.gbar.gitlab.shipment.persistence.dao;

import java.util.List;

public interface CrudInterface<T> {
    T getById(int id);

    void save(T t);

    List<T> getAll();

    void deleteById(int id);

    void deleteAll();

}
