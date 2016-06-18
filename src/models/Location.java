package models;

import services.validation.Validatable;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by chaymaebz on 29/04/16.
 */
public class Location implements Validatable{

    // Attributes

    private double latitude;
    private double longitude;

    // Constructor

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public ArrayList<Error> validate(Map<String, Object> additionalInfos) {
        return null;
    }
}
