package services.validation;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Salim on 5/9/16.
 */
public interface Validatable {

    ArrayList<Error> validate(Map<String, Object> additionalInfos);


}
