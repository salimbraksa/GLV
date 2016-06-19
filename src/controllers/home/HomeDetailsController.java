package controllers.home;

import controllers.Controller;
import controllers.forms.FormController;
import controllers.forms.FormControllerDelegate;
import helpers.AppModels;
import helpers.SBError;
import helpers.detailsViewHelpers.DetailsViewHelper;
import helpers.detailsViewHelpers.EmployeeDetailsViewHelper;
import helpers.interfaces.DetailsViewDataSource;
import helpers.shared.Shared;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import models.Employee;
import org.controlsfx.control.table.TableFilter;
import services.stores.EmployeeStore;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Salim on 6/15/16.
 */
public class HomeDetailsController extends Controller implements FormControllerDelegate, Initializable {

    // Attributes

    public DetailsViewDataSource<?> dataSource;
    public DetailsViewHelper modelHelper;

    public AppModels associatedModel;
    private ObservableList<Object> data;

    // Views

    @FXML private Label leftSummaryValue;
    @FXML private Label leftSummaryName;
    @FXML private Label rightSummaryValue;
    @FXML private Label rightSummaryName;

    @FXML private TableView<Object> tableView;

    private TableFilter filter;

    // Initializable

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Set up data
        data = FXCollections.observableArrayList();
        tableView.setItems(data);
    }


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
        for (String colName : modelHelper.getTableColumnNames()) {
            TableColumn column = new TableColumn(colName);
            column.setCellValueFactory(modelHelper.getPropertyValueFactoryFromColumnName(colName));
            tableView.getColumns().add(column);
        }

        // Initialize filter
        if (filter == null ) {
            // filter = new TableFilter(tableView);
        }

        // Update data
        data.clear();
        for (Object datum : dataSource.getItems()) {
            data.add(datum);
        }

    }

    // User Interaction

    @FXML
    private void addNewItem() {

        // Vehicle ability
        if (associatedModel == AppModels.Vehicle && Shared.ability.cannot("add vehicle")) {
            SBError error = new SBError("Error", "Admin privilege is required to perform this action");
            showError(error);
            return;
        }

        // Get appropriate form
        FormController<?> formController = associatedModel.getFormController(FormController.FormType.New);
        formController.delegate = this;

        // Show
        formController.show(400, 600);

    }

    @FXML
    private void editItem() {

        // Vehicle ability
        if (associatedModel == AppModels.Vehicle && Shared.ability.cannot("edit vehicle")) {
            SBError error = new SBError("Error", "Admin privilege is required to perform this action");
            showError(error);
            return;
        }

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

        // Check if employee is allowed to perform this action
        if (Shared.ability.cannot("delete resource")) {
            SBError error = new SBError("Error", "Admin privilege is required to perform this action");
            showError(error);
            return;
        }

        // Check if an item is not selected
        if (!itemIsSelected()) {
            SBError error = new SBError("Error", "Please select an item to show");
            showError(error);
        }

        // Get the item
        Object selectedItem = tableView.getSelectionModel().getSelectedItem();
        modelHelper.delete(selectedItem);
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
