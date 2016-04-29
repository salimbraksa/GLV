package models;

import java.util.Date;

/**
 * Created by chaymaebz on 29/04/16.
 */
public class Rent extends Location{

    // Attributes

    private int id;
    private int vehicule_id;
    private int customer_id;
    private Date start_date;
    private Date end_date;

    // Constructor

    public Rent(int id, int vehicule_id, int customer_id, Date start_date, Date end_date,
                double latitude, double longitude){
        super(latitude, longitude);
        this.id = id;
        this.customer_id = customer_id;
        this.vehicule_id = vehicule_id;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    // Getters
    
    public int getCustomer_id() {
        return customer_id;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public int getId() {
        return id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public int getVehicule_id() {
        return vehicule_id;
    }
}
