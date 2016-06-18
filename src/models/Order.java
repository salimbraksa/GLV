package models;

import services.validation.Validatable;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by chaymaebz on 29/04/16.
 */
public class Order implements Validatable {



    public enum Type {
        Order, Lease;
    }

    // Attributes

    private int id;
    private double cost;
    private int supplierId;
    private int vehiculeId;
    private Date date;

    // Construtor

    public Order(int id,double cost, Date date, int supplierId, int vehiculeId) {

        this.cost = cost;
        this.date = date;
        this.id = id;
        this.supplierId = supplierId;
        this.vehiculeId = vehiculeId;
    }

    // Getters

    public double getCost() {
        return cost;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public int getVehiculeId() {
        return vehiculeId;
    }

    public Type getType() {
        return Type.Order;
    }

    @Override
    public ArrayList<Error> validate(Map<String, Object> additionalInfos) {
        return null;
    }

}
