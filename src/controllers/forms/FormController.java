package controllers.forms;

import controllers.Controller;

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
    private boolean editable = false;
    private FormControllerDelegate delegate;

    // Getters & Setters

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean getEditable() {
        return editable;
    }

    public void setModel(Object model) {
        this.model = model;
    }

    public void setDelegate(FormControllerDelegate delegate) {
        this.delegate = delegate;
    }

}
