package models;

/**
 * Created by chaymaebz on 29/04/16.
 */
public class Location {

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
}
