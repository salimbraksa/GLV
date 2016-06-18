package services.CanCanCan;

import models.Admin;
import models.Employee;
import models.Manager;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by chaymaebz on 18/06/16.
 */
public class CanCanCan {
    // Attributes

    private ArrayList<String> abilities;

    //Methods

    public CanCanCan(Employee employee){
        String [] managerAbilities = { "add customer", "edit customer", "show customer",
                "add rent", "edit rent", "show rent",
                "show vehicule",
                "show order",
                "add lease", "edit lease", "show lease",
                "show supplier",
        };

        String [] additionalAbilities = { "add employee", "edit employee", "show employee", "delete employee",
                "delete customer",
                "delete rent",
                "add vehicule", "edit vehicule", "delete vehicule",
                "add order", "edit order", "delete order",
                "delete lease",
                "add supplier", "edit supplier", "delete supplier",
        };

        can(managerAbilities);

        if (employee instanceof Admin){
            can(additionalAbilities);
        }

    }

    public void can(String ability){
        abilities.add(ability);
    }

    public void can(String [] abilities){
        this.abilities.addAll(Arrays.asList(abilities));
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

