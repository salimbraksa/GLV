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
                    return "good";
                case Bad:
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
    private int currentStock;
    private int totalStock;

    // Constructor

    public Vehicule(int id, String type, double price, State state, int currentStock, int totalStock){
        this.id = id;
        this.type = type;
        this.price = price;
        this.state = state;
        this.currentStock = currentStock;
        this.totalStock = totalStock;
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

    @Override
    public String toString() {
        return "" + id + "-" + type;
    }

    @Override
    public ArrayList<Error> validate(Map<String, Object> additionalInfos) {
        return null;
    }
}
