package controllers.home;

import controllers.Controller;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import helpers.ControllerLoader;
import helpers.detailsViewDataSources.EmployeeDetailsViewDataSource;
import helpers.interfaces.DetailsViewDataSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import models.Employee;
import services.stores.EmployeeStore;
import services.stores.Store;
import services.stores.StoreType;
import views.menuItem.MenuItemCell;
import views.menuItem.MenuItemModel;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
        MenuItemModel usersItem = new MenuItemModel("Employees", "employee", FontAwesomeIcon.USER);
        MenuItemModel vehiclesItem = new MenuItemModel("Vehicles", "vehicle", FontAwesomeIcon.CAR);
        menuItems.addAll(usersItem, vehiclesItem);

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
    }

    public void cellClicked(MenuItemCell cell) {

        // If cell isn't there
        if (cell == null) { return; }

        // Get all items of selected resource
        String identifier = cell.getItem().getIdentifier();
        DetailsViewDataSource<?> dataSource = null;

        switch (identifier) {
            case "employee": dataSource = new EmployeeDetailsViewDataSource(EmployeeStore.sharedInstance().findAll()); break;
        }

        detailsViewIsEmpty = dataSource == null || dataSource.getItems().size() == 0;
        if (detailsViewIsEmpty) {
            setEmptyStateView();
            return;
        }
        setDetailView(dataSource);

    }

    // Helpers

    private void setEmptyStateView() {

        ControllerLoader loader = new ControllerLoader("/views/emptyStates/details_view_empty_state.fxml");
        Parent emptyStateView = loader.getScene().getRoot();
        configureDetailView(emptyStateView);
        detailPane.getChildren().setAll(emptyStateView);

    }

    private void setDetailView(DetailsViewDataSource<?> dataSource) {

        ControllerLoader loader = new ControllerLoader("/views/home/resource_index.fxml");
        Parent detailView = loader.getScene().getRoot();
        HomeDetailsController controller = (HomeDetailsController) loader.getController();
        controller.dataSource = dataSource;
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
