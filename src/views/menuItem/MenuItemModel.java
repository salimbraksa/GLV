package views.menuItem;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import helpers.AppModels;

/**
 * Created by Salim on 5/15/16.
 */
public class MenuItemModel {

    // Attributes

    private FontAwesomeIcon icon;

    private AppModels modelType;
    private String title;

    // Getters

    public String getTitle() {
        return title;
    }

    public FontAwesomeIcon getIcon() {
        return icon;
    }

    public AppModels getModelType() {
        return modelType;
    }

    // Constructor

    public MenuItemModel(String title, AppModels modelType, FontAwesomeIcon icon) {
        this.modelType = modelType;
        this.title = title;
        this.icon = icon;
    }

}
