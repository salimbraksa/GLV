package views.menuItem;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

/**
 * Created by Salim on 5/15/16.
 */
public class MenuItemView extends HBox {

    // Attributes

    @FXML
    public FontAwesomeIconView iconView;
    @FXML
    public Label title;

    // Constructors

    public MenuItemView() {
        super();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu-item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

}
