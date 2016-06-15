package services.stores;

import org.jetbrains.annotations.Nullable;

/**
 * Created by Salim on 6/15/16.
 */
final public class Store {

    private Store() {

    }

    @Nullable public static StoreType<?> storeFromIdentifier(String identifier) {

        switch (identifier) {
            case "employee": return EmployeeStore.sharedInstance();
            case "customer": return CustomerStore.sharedInstance();
            default: return null;
        }

    }

}
