package views.menuItem;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

/**
 * Created by Salim on 5/15/16.
 */
public class MenuItemCell extends ListCell<MenuItemModel> {

    @Override
    protected void updateItem(MenuItemModel item, boolean empty) {
        super.updateItem(item, empty);

        // Handling the case of empty
        if (empty) {
            setText(null);
            setGraphic(null);
            return;
        }

        // Load menu-item.fxml
        try {
            MenuItemView menuItem = new MenuItemView();
            menuItem.iconView.setIcon(item.getIcon());
            menuItem.title.setText(item.getTitle());
            setGraphic(menuItem);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
