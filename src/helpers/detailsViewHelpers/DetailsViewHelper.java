package helpers.detailsViewHelpers;

import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by Salim on 6/18/16.
 */
public abstract class DetailsViewHelper {

    /// Returns the name of columns used in the table view of DetailsViewController
    public abstract String[] getTableColumnNames();

    /// Returns Property Value Factory from column name
    public abstract PropertyValueFactory getPropertyValueFactoryFromColumnName(String columnName);

}
