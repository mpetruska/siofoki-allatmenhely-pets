package models.db
// AUTO-GENERATED Slick data model for table Video
trait VideoTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Video
   *  @param id Database column ID SqlType(SMALLINT), AutoInc, PrimaryKey
   *  @param dogId Database column DOG_ID SqlType(SMALLINT)
   *  @param link Database column LINK SqlType(VARCHAR), Length(100,true)
   *  @param lastUpdate Database column LAST_UPDATE SqlType(TIMESTAMP) */
  case class VideoRow(id: Int, dogId: Int, link: String, lastUpdate: java.sql.Timestamp)
  /** GetResult implicit for fetching VideoRow objects using plain SQL queries */
  implicit def GetResultVideoRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp]): GR[VideoRow] = GR{
    prs => import prs._
    VideoRow.tupled((<<[Int], <<[Int], <<[String], <<[java.sql.Timestamp]))
  }
  /** Table description of table video. Objects of this class serve as prototypes for rows in queries. */
  class Video(_tableTag: Tag) extends profile.api.Table[VideoRow](_tableTag, Some("pets"), "video") {
    def * = (id, dogId, link, lastUpdate) <> (VideoRow.tupled, VideoRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(dogId), Rep.Some(link), Rep.Some(lastUpdate))).shaped.<>({r=>import r._; _1.map(_=> VideoRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(SMALLINT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column DOG_ID SqlType(SMALLINT) */
    val dogId: Rep[Int] = column[Int]("DOG_ID")
    /** Database column LINK SqlType(VARCHAR), Length(100,true) */
    val link: Rep[String] = column[String]("LINK", O.Length(100,varying=true))
    /** Database column LAST_UPDATE SqlType(TIMESTAMP) */
    val lastUpdate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("LAST_UPDATE")

    /** Index over (dogId) (database name DOG_ID) */
    val index1 = index("DOG_ID", dogId)
  }
  /** Collection-like TableQuery object for table Video */
  lazy val Video = new TableQuery(tag => new Video(tag))
}
