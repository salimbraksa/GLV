package models;

import java.util.Date;

/**
 * Created by chaymaebz on 29/04/16.
 */
public class Lease extends Order {

    // Add attribute : end_date

    private Date end_date;

    // Constructor

    public Lease(double cost, Date date, int id, int supplier_id, int vehicule_id, Date end_date){
        super(cost, date, id, supplier_id, vehicule_id);
        this.end_date = end_date;
    }

    // Add end_date getter


    public Date getEnd_date() {
        return end_date;
    }
}
