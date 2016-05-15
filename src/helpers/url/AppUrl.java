package helpers.url;

/**
 * Created by chaymaebz on 15/05/16.
 */
public class AppUrl {

    // Attributes
    static String url = System.getProperty("user.dir");

    public String getUrl(){
        return url;
    }

    private AppUrl(){}

}

