package models;

import java.util.Date;

/**
 * Created by chaymaebz on 29/04/16.
 */
public class Lease extends Order {

    // Attributes

    private Date endDate;

    // Constructor

    public Lease(double cost, Date date, int id, int supplierId, int vehiculeId, Date endDate){
        super(cost, date, id, supplierId, vehiculeId);
        this.endDate = endDate;
    }

    // Getters

    public Date getEndDate() {
        return endDate;
    }
    
}
