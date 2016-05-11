package models;

/**
 * Created by chaymaebz on 29/04/16.
 */
public class Vehicule {

    public enum State {
        good, bad;

        public String rawValue() {
            switch (this) {
                case good:
                    return "good";
                case bad:
                    return "bad";
               }
            return null;
        }
    }

    // Attributes

    private int id;
    private String type;
    private double price;
    private State state;

    // Constructor

    public Vehicule(int id, String type, double price, State state){
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

    public State getState() {
        return state;
    }

    public String getType() {
        return type;
    }
}
