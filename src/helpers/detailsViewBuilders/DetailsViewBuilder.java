package helpers.detailsViewBuilders;

import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Created by Salim on 6/18/16.
 */
public interface DetailsViewBuilder {

    /// Returns the name of columns used in the table view of DetailsViewController
    String[] getTableColumnNames();

    /// Returns Property Value Factory from column name
    PropertyValueFactory getPropertyValueFactoryFromColumnName(String columnName);

}
