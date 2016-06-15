package controllers.home;

import controllers.Controller;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.util.Callback;
import views.menuItem.MenuItemCell;
import views.menuItem.MenuItemModel;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Salim on 5/15/16.
 */
public class HomeController extends Controller implements Initializable {

    // Views

    @FXML
    ListView<MenuItemModel> listView;
    ObservableList<MenuItemModel> menuItems;


    // Initializers

    public HomeController() {

        menuItems = FXCollections.observableArrayList();
        MenuItemModel usersItem = new MenuItemModel("Users", "users", FontAwesomeIcon.USER);
        MenuItemModel vehiclesItem = new MenuItemModel("Vehicles", "vehicles", FontAwesomeIcon.CAR);
        menuItems.addAll(usersItem, vehiclesItem);

    }

    public void initialize(URL location, ResourceBundle resources) {

        listView.setItems(menuItems);
        listView.setCellFactory(new Callback<ListView<MenuItemModel>, ListCell<MenuItemModel>>() {
            @Override
            public ListCell<MenuItemModel> call(ListView<MenuItemModel> param) {
                return new MenuItemCell();
            }
        });

    }

    // User Interactions

    @FXML
    void signOut() {
    }

}
