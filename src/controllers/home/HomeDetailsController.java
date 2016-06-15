package controllers.home;

import controllers.Controller;
import helpers.interfaces.DetailsViewDataSource;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Created by Salim on 6/15/16.
 */
public class HomeDetailsController extends Controller {

    // Attributes

    public DetailsViewDataSource<?> dataSource;

    // Views

    @FXML private Label leftSummaryValue;
    @FXML private Label leftSummaryName;
    @FXML private Label rightSummaryValue;
    @FXML private Label rightSummaryName;

    // Methods

    public void reloadData() {

        // Quick check on dataSource
        if (dataSource == null) { return; }

        // Update Summary Values & Names
        leftSummaryValue.setText(dataSource.valueOfSummaryAtIndex(0));
        rightSummaryValue.setText(dataSource.valueOfSummaryAtIndex(1));
        leftSummaryName.setText(dataSource.nameOfSummaryAtIndex(0));
        rightSummaryName.setText(dataSource.nameOfSummaryAtIndex(1));

    }

}
