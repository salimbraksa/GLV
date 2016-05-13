package helpers;

/**
 * Created by Salim on 5/13/16.
 */
public class SBError extends Error {

    // Attributes

    private String title;

    // Getters & Setters

    public String getTitle() {
        return title;
    }

    // Constructor

    public SBError(String title, String message) {
        super(message);
        this.title = title;
    }

}

