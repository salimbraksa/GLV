package models;

import java.util.Date;

/**
 * Created by chaymaebz on 29/04/16.
 */
public class Lease extends Order {

    // Attributes

    private Date endDate;

    // Constructor

    public Lease(int id, double cost, Date date, int supplierId, int vehiculeId, Date endDate){
        super(id, cost, date, supplierId, vehiculeId);
        this.endDate = endDate;
    }

    // Getters

    public Date getEndDate() {
        return endDate;
    }
    
}
