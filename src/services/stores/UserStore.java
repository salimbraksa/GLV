package services.stores;

import models.User;
import services.mysql.Mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by Salim on 5/4/16.
 */
public class UserStore implements StoreType<User>  {

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
        String request = "DELETE FROM User WHERE id=" + id;
        mysql.executeUpdate(request);
    }

    @Override
    public void update(int id, User object) {
    }

    @Override
    public User find(int id) {
        ResultSet result = mysql.executeQuery("SELECT * FROM user WHERE id="+id);

        //Retrieve data by column name
        try {
            int userId = result.getInt("id");
            String userFirstName = result.getString("first_name");
            String userLastName = result.getString("last_name");
            String userSexe = result.getString("sexe");
            String userEmail = result.getString("email");
            String userPhone = result.getString("phone");
            String userType = result.getString("type");
            




        } catch (SQLException e) {
            e.printStackTrace();
        }

        ///User user = new User();
        return null;
    }

    @Override
    public ArrayList<User> findAll() {
        return null;
    }

}