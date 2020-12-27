package extensions

import java.time.OffsetDateTime
import java.sql.Timestamp

package object time {
  
  def offsetDateTimeToUtcTimestamp(x: OffsetDateTime): Timestamp = {
    Timestamp.from(x.toInstant())
  }

}
