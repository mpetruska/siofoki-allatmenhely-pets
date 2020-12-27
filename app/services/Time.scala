package services

import java.time.{OffsetDateTime, ZoneOffset}

class MockableTime {

  def currentTime(): OffsetDateTime = OffsetDateTime.now()

  def currentUtcTime(): OffsetDateTime = currentTime().withOffsetSameInstant(ZoneOffset.UTC)

}
