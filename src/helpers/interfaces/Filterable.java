package helpers.interfaces;

import java.util.ArrayList;

/**
 * Created by Salim on 5/11/16.
 */
public interface Filterable<T> {

    public ArrayList<T> filterBy(FilterOptionType option, String value);

}
