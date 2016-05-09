package services.validation;

/**
 * Created by Salim on 5/9/16.
 */
public class Validator<T> {

    // Properties

    private T resource;

    // Constructor

    public Validator(T resource) {
        this.resource = resource;
    }

}
