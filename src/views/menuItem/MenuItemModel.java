package views.menuItem;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

/**
 * Created by Salim on 5/15/16.
 */
public class MenuItemModel {

    // Attributes

    private String identifier;
    private FontAwesomeIcon icon;
    private String title;

    public String getTitle() {
        return title;
    }

    public FontAwesomeIcon getIcon() {
        return icon;
    }

    public String getIdentifier() {
        return identifier;
    }

    // Constructor

    public MenuItemModel(String title, String identifier, FontAwesomeIcon icon) {
        this.identifier = identifier;
        this.title = title;
        this.icon = icon;
    }

}
