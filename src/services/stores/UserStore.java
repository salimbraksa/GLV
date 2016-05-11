package services.stores;

import helpers.interfaces.FilterOptionType;
import helpers.interfaces.Filterable;
import models.User;
import services.mysql.Mysql;

import java.util.ArrayList;


/**
 * Created by Salim on 5/4/16.
 */
public class UserStore implements StoreType<User> {

    // Singleton Implementation

    static private UserStore singleton = new UserStore();

    static public UserStore sharedInstance() { return singleton; }

    private UserStore() { }

    Mysql mysql = Mysql.sharedInstance();

    // CRUD Methods

    @Override
    public void create(User object) {
    }

    @Override
    public void delete(int id) {
        String request = "DELETE FROM User WHERE id=" + id+";";
        mysql.executeUpdate(request);
    }

    @Override
    public void update(int id, User object) {
    }

    @Override
    public User find(int id) { return null; }

    @Override
    public ArrayList<User> findAll() {
        return null;
    }

}
