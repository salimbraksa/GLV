package services.stores;

import models.Vehicule;

import java.util.ArrayList;

/**
 * Created by Salim on 5/4/16.
 */
public interface StoreType<T> {

    // Creates a new resource and store it in the database
    void create(T object);

    // Deletes resource of given in
    void delete(int id);

    // Updates resource
    void update(int id, T object);

    // Fetch resource
    T find(int id);

    // Fetch all resources
    ArrayList<T> findAll();


}
