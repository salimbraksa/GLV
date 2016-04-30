package models;

import java.util.Date;

/**
 * Created by chaymaebz on 29/04/16.
 */
public class Order {

    // Attributes

    private int id;
    private double cost;
    private int supplier_id;
    private int vehicule_id;
    private Date date;

    // Construtor

    public Order(double cost, Date date, int id, int supplier_id, int vehicule_id) {
        this.cost = cost;
        this.date = date;
        this.id = id;
        this.supplier_id = supplier_id;
        this.vehicule_id = vehicule_id;
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

    public int getSupplier_id() {
        return supplier_id;
    }

    public int getVehicule_id() {
        return vehicule_id;
    }
}
