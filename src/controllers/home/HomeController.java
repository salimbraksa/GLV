package controllers.home;

import controllers.Controller;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import helpers.AppModels;
import helpers.ControllerLoader;
import helpers.detailsViewHelpers.DetailsViewHelper;
import helpers.detailsViewHelpers.EmployeeDetailsViewHelper;
import helpers.detailsViewDataSources.*;
import helpers.interfaces.DetailsViewDataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import models.*;
import views.menuItem.MenuItemCell;
import views.menuItem.MenuItemModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Supplier;

/**
 * Created by Salim on 5/15/16.
 */
public class HomeController extends Controller implements Initializable {

    // Attributes

    private boolean detailsViewIsEmpty = false;

    // Views

    @FXML
    ListView<MenuItemModel> listView;
    ObservableList<MenuItemModel> menuItems;

    @FXML
    AnchorPane detailPane;

    // Initializers

    public HomeController() {

        menuItems = FXCollections.observableArrayList();
        MenuItemModel employeesItem = new MenuItemModel("Employees", AppModels.Employee, FontAwesomeIcon.USER);
        MenuItemModel customersItem = new MenuItemModel("Customers", AppModels.Customer, FontAwesomeIcon.USERS);
        MenuItemModel rentsItem = new MenuItemModel("Rents", AppModels.Rent, FontAwesomeIcon.HOME);
        MenuItemModel vehiclesItem = new MenuItemModel("Vehicles", AppModels.Vehicle, FontAwesomeIcon.CAR);
        MenuItemModel suppliersItem = new MenuItemModel("Suppliers", AppModels.Supplier, FontAwesomeIcon.INDUSTRY);
        MenuItemModel moneyItem = new MenuItemModel("Orders", AppModels.Order, FontAwesomeIcon.MONEY);
        menuItems.addAll(employeesItem, customersItem, rentsItem, vehiclesItem, suppliersItem, moneyItem);

    }

    public void initialize(URL location, ResourceBundle resources) {

        listView.setItems(menuItems);
        listView.setCellFactory(new Callback<ListView<MenuItemModel>, ListCell<MenuItemModel>>() {
            @Override
            public ListCell<MenuItemModel> call(ListView<MenuItemModel> param) {

                final MenuItemCell cell = new MenuItemCell();

                cell.setOnMouseClicked(new EventHandler<MouseEvent>(){

                    @Override
                    public void handle(MouseEvent event) {
                        cellClicked(cell);
                    }

                });

                return cell;
            }

        });

    }

    // User Interactions

    @FXML
    void signOut() {
        ControllerLoader loader = new ControllerLoader("/views/login/login.fxml");
        loader.getController().show(600, 400);
        window.close();
    }

    public void cellClicked(MenuItemCell cell) {

        // If cell isn't there
        if (cell == null) { return; }

        // Get all items of selected resource
        AppModels modelType = cell.getItem().getModelType();
        DetailsViewDataSource<?> dataSource = null;
        DetailsViewHelper viewBuilder = null;

        switch (modelType) {
            case Employee:
                dataSource = new EmployeeDetailsViewDataSource();
                viewBuilder = new EmployeeDetailsViewHelper();
                break;

            case Customer:
                dataSource = new CustomerDetailsViewDataSource( (ArrayList<Customer>) modelType.getStore().findAll()); break;
            case Supplier:
                dataSource = new SupplierDetailsViewDataSource( (ArrayList<Supplier>) modelType.getStore().findAll()); break;
            case Vehicle:
                dataSource = new VehicleDetailsViewDataSource( (ArrayList<Vehicule>) modelType.getStore().findAll()); break;
            case Rent:
                dataSource = new RentDetailsViewDataSource( (ArrayList<Rent>) modelType.getStore().findAll()); break;
            case Order:
                dataSource = new OrderDetailsViewDataSource( (ArrayList<Order>) modelType.getStore().findAll()); break;
        }

        detailsViewIsEmpty = dataSource == null || dataSource.getItems().size() == 0;
        if (detailsViewIsEmpty) {
            setEmptyStateView();
            return;
        }
        setDetailView(dataSource, viewBuilder, modelType);

    }

    // Helpers

    private void setEmptyStateView() {

        ControllerLoader loader = new ControllerLoader("/views/emptyStates/details_view_empty_state.fxml");
        Parent emptyStateView = loader.getScene().getRoot();
        configureDetailView(emptyStateView);
        detailPane.getChildren().setAll(emptyStateView);

    }

    private void setDetailView(DetailsViewDataSource<?> dataSource, DetailsViewHelper viewBuilder, AppModels associatedModel) {

        ControllerLoader loader = new ControllerLoader("/views/home/resource_index.fxml");
        Parent detailView = loader.getScene().getRoot();
        HomeDetailsController controller = (HomeDetailsController) loader.getController();
        controller.dataSource = dataSource;
        controller.associatedModel = associatedModel;
        controller.modelHelper = viewBuilder;
        controller.reloadData();
        configureDetailView(detailView);
        detailPane.getChildren().setAll(detailView);

    }

    private void configureDetailView(Node view) {
        AnchorPane.setTopAnchor(view, 0.0);
        AnchorPane.setLeftAnchor(view, 0.0);
        AnchorPane.setRightAnchor(view, 0.0);
        AnchorPane.setBottomAnchor(view, 0.0);
    }

}
