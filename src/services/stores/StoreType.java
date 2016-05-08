package services.stores;

import java.util.ArrayList;

/**
 * Created by Salim on 5/4/16.
 */
public interface StoreType<T> {

    // Creates a new resource and store it in the database
    public void create(T object);

    // Deletes resource of given in
    public void delete(int id);

    // Updates resource
    public void update(int id, T object);

    // Fetch resource
    public T find(int id);

    // Fetch all resources
    public ArrayList<T> findAll();

}
