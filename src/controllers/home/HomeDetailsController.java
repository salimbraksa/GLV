package controllers.home;

import controllers.Controller;
import controllers.forms.FormController;
import controllers.forms.FormControllerDelegate;
import helpers.AppModels;
import helpers.SBError;
import helpers.interfaces.DetailsViewDataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Created by Salim on 6/15/16.
 */
public class HomeDetailsController extends Controller implements FormControllerDelegate {

    // Attributes

    public DetailsViewDataSource<?> dataSource;
    public AppModels associatedModel;
    private ObservableList<Object> data;

    // Views

    @FXML private Label leftSummaryValue;
    @FXML private Label leftSummaryName;
    @FXML private Label rightSummaryValue;
    @FXML private Label rightSummaryName;

    @FXML private TableView<Object> tableView;

    // Methods

    public void reloadData() {

        // Quick check on dataSource
        if (dataSource == null) { return; }

        // Update Summary Values & Names
        leftSummaryValue.setText(dataSource.valueOfSummaryAtIndex(0));
        rightSummaryValue.setText(dataSource.valueOfSummaryAtIndex(1));
        leftSummaryName.setText(dataSource.nameOfSummaryAtIndex(0));
        rightSummaryName.setText(dataSource.nameOfSummaryAtIndex(1));

        // Add Table View Columns
        tableView.getItems().clear();
        tableView.getColumns().clear();
        for (String colName : dataSource.getTableViewColumns()) {
            TableColumn column = new TableColumn(colName);
            column.setCellValueFactory(dataSource.getPropertyValueFactoryForColumn(colName));
            tableView.getColumns().add(column);
        }

        // Update data
        data = FXCollections.observableArrayList();
        for (Object datum : dataSource.getItems()) {
            data.add(datum);
        }
        tableView.setItems(data);

    }

    // User Interaction

    @FXML
    private void addNewItem() {

        // Get appropriate form
        FormController<?> formController = associatedModel.getFormController(FormController.FormType.New);

        // Show
        formController.show(400, 600);

    }

    @FXML
    private void editItem() {

        // Get appropriate form
        FormController<?> formController = associatedModel.getFormController(FormController.FormType.Edit);
        Object selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            SBError error = new SBError("Error", "Please select an item to edit");
            showError(error);
            return;
        }
        formController.setModel(selectedItem);

        // Show
        formController.show(400, 600);

    }

    @FXML
    private void showItem() {

        // Get appropriate form
        FormController<?> formController = associatedModel.getFormController(FormController.FormType.Show);
        Object selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            SBError error = new SBError("Error", "Please select an item to show");
            showError(error);
            return;
        }
        formController.setModel(selectedItem);

        // Show
        formController.show(400, 600);

    }

    // Form Controller Delegate

    @Override
    public void didFinishEditingWithModel(Object model) {

    }

    // Helpers

    private void showError(SBError error) {
        String title = ((SBError) error).getTitle();
        String message = error.getMessage();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
