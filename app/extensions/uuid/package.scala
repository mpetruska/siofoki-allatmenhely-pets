package extensions

import java.util.UUID
import java.nio.ByteBuffer
import java.sql.Blob

import javax.sql.rowset.serial.SerialBlob
import scalaz.syntax.std.boolean._

import scala.util.Try

package object uuid {

  val uuidLength = 16

  def uuidToByteArray(uuid: UUID): Array[Byte] = {
    ByteBuffer
      .allocate(16)
      .putLong(uuid.getMostSignificantBits())
      .putLong(uuid.getLeastSignificantBits())
      .array()
  }

  def uuidToBlob(uuid: UUID): Blob = {
    new SerialBlob(uuidToByteArray(uuid))
  }

  def uuidFromBlob(blob: Blob): Option[UUID] = {
    (blob.length() == uuidLength)
      .option(blob.getBytes(1L, uuidLength))
      .map(ByteBuffer.wrap)
      .map { buffer =>
        new UUID(buffer.getLong(), buffer.getLong())
      }
  }

  def uuidFromString(x: String): Option[UUID] = {
    Try(UUID.fromString(x)).toOption
  }

}
