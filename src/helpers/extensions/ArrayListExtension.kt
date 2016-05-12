package helpers.extensions

import java.util.*

/**
 * Created by Salim on 5/12/16.
 */

fun <E> ArrayList<E>.getFirst(): E? {
    if (this.size > 0) {
        return this.get(0);
    }
    return null;
}

fun <E> ArrayList<E>.getLast(): E? {
    if (this.size > 0) {
        return this.get(size-1);
    }
    return null;
}