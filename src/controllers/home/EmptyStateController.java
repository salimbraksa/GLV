package controllers.home;

import controllers.Controller;
import controllers.forms.FormController;
import helpers.detailsViewHelpers.DetailsViewHelper;
import javafx.fxml.FXML;

/**
 * Created by Salim on 6/18/16.
 */
public class EmptyStateController extends Controller {

    // Attributes

    public DetailsViewHelper modelHelper;

    // User Interaction

    @FXML
    private void newItem() {
        FormController<?> formController = modelHelper.formControllerForOperation(FormController.FormType.New);
        formController.delegate = (HomeController) parentController;
        formController.show(400,600);
    }

}
