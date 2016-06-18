package helpers.extensions

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Salim on 5/10/16.
 */

fun Date.getTimestamp(): String {

    val sq = Timestamp(this.time)
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    return formatter.format(sq)

}