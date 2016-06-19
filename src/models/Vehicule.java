package models;

import services.validation.Validatable;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by chaymaebz on 29/04/16.
 */
public class Vehicule implements Validatable{

    public enum State {
        Good, Bad;

        public String rawValue() {
            switch (this) {
                case Good:
                    return "Good";
                case Bad:
                    return "Bad";
               }
            return null;
        }

    }

    // Attributes

    private int id;
    private String type;
    private double price;
    private State state;
    public int currentStock;
    private int totalStock;
    private String image;

    // Constructor

    public Vehicule(int id, String type, double price, State state, int currentStock, int totalStock, String image){
        this.id = id;
        this.type = type;
        this.price = price;
        this.state = state;
        this.currentStock = currentStock;
        this.totalStock = totalStock;
        this.image = image;
    }



    public Vehicule(int id){
        this.id = id;
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

    public int getCurrentStock() {
        return currentStock;
    }

    public int getTotalStock() {
        return totalStock;
    }

    public String getType() {
        return type;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "" + id + "-" + type;
    }

    @Override
    public ArrayList<Error> validate(Map<String, Object> additionalInfos) {
        return null;
    }
}
