package models;

import java.util.Date;

/**
 * Created by chaymaebz on 29/04/16.
 */
public class Rent {

    // Attributes

    private int id;
    private int vehiculeId;
    private int customerId;
    private Date startDate;
    private Date endDate;
    private String pickupLocation;
    private String dropLocation;

    // Constructor

    public Rent(int id, int vehiculeId, int customerId, Date startDate, Date endDate,
                String pickupLocation, String dropLocation){

        this.id = id;
        this.customerId = customerId;
        this.vehiculeId = vehiculeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
    }

    // Getters
    
    public int getCustomerId() {
        return customerId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getVehiculeId() {
        return vehiculeId;
    }

    public String getDropLocation() { return dropLocation; }

    public String getPickupLocation() { return pickupLocation; }
}
