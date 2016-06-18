package helpers.detailsViewHelpers;

import controllers.forms.FormController;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by Salim on 6/18/16.
 */
public abstract class DetailsViewHelper {

    /// Returns the name of columns used in the table view of DetailsViewController
    public abstract String[] getTableColumnNames();

    /// Returns Property Value Factory from column name
    public abstract PropertyValueFactory getPropertyValueFactoryFromColumnName(String columnName);

    /// Deletes object from the database
    public abstract void delete(Object object);

    /// Returns the form associated with the model
    public abstract FormController<?> formControllerForOperation(FormController.FormType operation);

}
