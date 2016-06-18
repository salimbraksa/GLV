package models;

import services.validation.Validatable;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

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

    @Override
    public Type getType() {
        return Type.Lease;
    }

    @Override
    public ArrayList<Error> validate(Map<String, Object> additionalInfos) {
        return null;
    }
}
