package services.CanCanCan;

import models.Admin;
import models.Employee;
import models.Manager;

import java.util.ArrayList;

/**
 * Created by chaymaebz on 18/06/16.
 */
public class CanCanCan {
    // Attributes

    private ArrayList<String> abilities;

    //Methods

    public CanCanCan(Employee employee){

        if (employee instanceof Admin){
            // here goes the code for Admin abilities
        }

        else if (employee instanceof Manager){
            // here goes the code for Manager abilities
        }
    }

    public void can(String ability){
        abilities.add(ability);
    }

    public boolean hasAbility(String ability) {
        
        if (abilities.contains(ability)){
            return true;
        }
        else {
            return false;
        }
    }

    }

