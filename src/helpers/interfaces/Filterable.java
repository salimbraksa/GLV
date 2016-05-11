package helpers.interfaces;

import java.util.ArrayList;

/**
 * Created by Salim on 5/11/16.
 */
public interface Filterable<T> {

    public ArrayList<T> filterBy(FilterOptionsType options, String value);

}
