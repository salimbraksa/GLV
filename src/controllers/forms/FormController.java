package controllers.forms;

import controllers.Controller;
import javafx.fxml.FXML;

/**
 * Created by Salim on 6/15/16.
 */
public class FormController<T> extends Controller {

    public enum FormType {
        Show, Edit, New;

        public String rawValue() {
            switch (this) {
                case Show: return "show";
                case Edit: return "edit";
                case New: return "new";
                default: return null;
            }
        }

    }

    // Attributes

    private Object model;
    public FormControllerDelegate delegate;

    // Getters & Setters

    public void setModel(Object model) {
        this.model = model;
    }

    @FXML
    void cancelAction() {
        window.close();
    }

}
