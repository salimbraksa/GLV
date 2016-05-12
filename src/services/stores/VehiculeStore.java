package services.stores;

import models.Vehicule;
import services.mysql.Mysql;

import java.util.ArrayList;

/**
 * Created by chaymaebz on 11/05/16.
 */
public class VehiculeStore implements StoreType<Vehicule> {

    //singleton implementation

    static private VehiculeStore singleton = new VehiculeStore();

    static public VehiculeStore sharedInstance(){ return singleton; }

    private VehiculeStore() { }

    //database manager

    Mysql mysql = Mysql.sharedInstance();


    @Override
    public void create(Vehicule object) {

        String request = "INSERT INTO vehicule (type, price, state) VALUES ('"+object.getType()+"', '"+object.getPrice()+
                "', '"+object.getState().rawValue()+"');";
        mysql.executeQuery(request);
    }

    @Override
    public void delete(int id) { UserStore.sharedInstance().delete(id); }

    @Override
    public void update(int id, Vehicule object) {


        mysql.executeQuery("UPDATE vehicule" +
                "SET type="+object.getType()+", price="+object.getPrice()+", state"+object.getState().rawValue()+
                "WHERE id="+id+";");

    }

    @Override
    public Vehicule find(int id) {
        return null;
    }

    @Override
    public ArrayList<Vehicule> findAll() {
        return null;
    }
}
