package helpers.interfaces;

import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

/**
 * Created by Salim on 6/15/16.
 */
public interface DetailsViewDataSource<T> {

    String valueOfSummaryAtIndex(int index);

    String nameOfSummaryAtIndex(int index);

    ArrayList<T> getItems();

    String[] getTableViewColumns();

    PropertyValueFactory getPropertyValueFactoryForColumn(String colName);

}
