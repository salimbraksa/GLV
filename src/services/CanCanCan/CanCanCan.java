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

    private ArrayList<String> disabilities;

    //Methods

    public CanCanCan(Employee employee){

        // Init disabilities
        disabilities = new ArrayList<>();

        // The admin can do everything
        if (employee instanceof Admin) { return; }

        // However ...
        String[] disabilities = new String[10];
        disabilities[0] = "manage employee";
        disabilities[1] = "delete resource";
        disabilities[2] = "add vehicle";
        disabilities[3] = "edit vehicle";
        disabilities[4] = "manage supplier";
        disabilities[5] = "manage order";
        addDisabilities(disabilities);

    }

    public void addDisabilities(String [] disabilities){
        this.disabilities.addAll(Arrays.asList(disabilities));
    }

    public boolean cannot(String disability) {
        return disabilities.contains(disability);
    }

}

