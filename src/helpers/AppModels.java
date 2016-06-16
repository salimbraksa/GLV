package helpers;

import controllers.forms.FormController;
import services.stores.*;

/**
 * Created by Salim on 6/15/16.
 */
public enum AppModels {

    Employee,
    Customer,
    Rent,
    Vehicle,
    Order,
    Supplier;

    // Methods

    public String rawValue() {
        switch (this) {
            case Employee: return "employee";
            case Customer: return "customer";
            case Rent: return "rent";
            case Vehicle: return "vehicle";
            case Supplier: return "supplier";
            case Order: return "order";
            default: return null;
        }
    }

    public StoreType<?> getStore() {
        switch (this) {
            case Employee: return EmployeeStore.sharedInstance();
            case Customer: return CustomerStore.sharedInstance();
            case Supplier: return SupplierStore.sharedInstance();
            case Rent: return RentStore.sharedInstance();
            case Vehicle: return VehiculeStore.sharedInstance();
            default: return null;
        }
    }

    public FormController<?> getFormController(FormController.FormType type) {
        String fxmlName = type.rawValue() + "_" + rawValue() + "_form.fxml";
        System.out.println("URL: " + fxmlName);
        ControllerLoader loader = new ControllerLoader("/views/forms/"+rawValue()+"/"+fxmlName);
        FormController<?> controller = (FormController<?>) loader.getController();
        return controller;
    }

}
