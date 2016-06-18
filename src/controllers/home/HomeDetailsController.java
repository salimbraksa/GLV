package controllers.home;

import controllers.Controller;
import controllers.forms.FormController;
import controllers.forms.FormControllerDelegate;
import helpers.AppModels;
import helpers.SBError;
import helpers.detailsViewBuilders.DetailsViewBuilder;
import helpers.detailsViewBuilders.EmployeeDetailsViewBuilder;
import helpers.interfaces.DetailsViewDataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.Employee;
import org.controlsfx.control.table.TableFilter;
import services.stores.EmployeeStore;

/**
 * Created by Salim on 6/15/16.
 */
public class HomeDetailsController extends Controller implements FormControllerDelegate {

    // Attributes

    public DetailsViewDataSource<?> dataSource;
    public DetailsViewBuilder viewBuilder;

    public AppModels associatedModel;
    private ObservableList<Object> data;

    // Views

    @FXML private Label leftSummaryValue;
    @FXML private Label leftSummaryName;
    @FXML private Label rightSummaryValue;
    @FXML private Label rightSummaryName;

    @FXML private TableView<Object> tableView;

    private TableFilter filter;

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
        tableView.getColumns().clear();
        for (String colName : viewBuilder.getTableColumnNames()) {
            TableColumn column = new TableColumn(colName);
            column.setCellValueFactory(viewBuilder.getPropertyValueFactoryFromColumnName(colName));
            tableView.getColumns().add(column);
        }

        // Update data
        if (data != null) {
            data.clear();
        } else {
            data = FXCollections.observableArrayList();
        }
        for (Object datum : dataSource.getItems()) {
            data.add(datum);
        }
        tableView.setItems(data);

        // Set filter
        if (filter == null) {
            filter = new TableFilter(tableView);
        }

    }

    // User Interaction

    @FXML
    private void addNewItem() {

        // Get appropriate form
        FormController<?> formController = associatedModel.getFormController(FormController.FormType.New);
        formController.delegate = this;

        // Show
        formController.show(400, 600);

    }

    @FXML
    private void editItem() {

        // Get appropriate form
        FormController<?> formController = associatedModel.getFormController(FormController.FormType.Edit);
        formController.delegate = this;
        Object selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (!itemIsSelected()) {
            SBError error = new SBError("Error", "Please select an item to show");
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
        if (!itemIsSelected()) {
            SBError error = new SBError("Error", "Please select an item to show");
            showError(error);
            return;
        }
        formController.setModel(selectedItem);

        // Show
        formController.show(400, 600);

    }

    @FXML
    private void deleteItem() {

        // Check if an item is not selected
        if (!itemIsSelected()) {
            SBError error = new SBError("Error", "Please select an item to show");
            showError(error);
        }

        // Get the item
        Object selectedItem = tableView.getSelectionModel().getSelectedItem();
        Employee employee = ((EmployeeDetailsViewBuilder) viewBuilder).castedItem(selectedItem);
        EmployeeStore.sharedInstance().delete(employee.getId());
        dataSource.reloadItems();
        reloadData();

    }

    // Form Controller Delegate

    @Override
    public void didUpdateDatabase() {
        dataSource.reloadItems();
        reloadData();
    }

    // Helpers

    private boolean itemIsSelected() {
        Object selectedItem = tableView.getSelectionModel().getSelectedItem();
        return selectedItem != null;
    }

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
