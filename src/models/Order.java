package models;

import java.util.Date;

/**
 * Created by chaymaebz on 29/04/16.
 */
public class Order {

    // Attributes

    private int id;
    private double cost;
    private int supplierId;
    private int vehiculeId;
    private Date date;

    // Construtor

    public Order(double cost, Date date, int id, int supplierId, int vehiculeId) {
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
}
