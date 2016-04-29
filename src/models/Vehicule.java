package models;

/**
 * Created by chaymaebz on 29/04/16.
 */
public class Vehicule {

    // Attributes

    private int id;
    private String type;
    private double price;
    private String state;

    // Constructor

    public Vehicule(int id, String type, double price, String state){
        this.id = id;
        this.type = type;
        this.price = price;
        this.state = state;
    }

    // Getters


    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getState() {
        return state;
    }

    public String getType() {
        return type;
    }
}
