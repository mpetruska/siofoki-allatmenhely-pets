package models.db
// AUTO-GENERATED Slick data model for table ArchiveLog
trait ArchiveLogTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table ArchiveLog
   *  @param id Database column ID SqlType(INT), AutoInc, PrimaryKey
   *  @param date Database column DATE SqlType(TIMESTAMP)
   *  @param userId Database column USER_ID SqlType(INT)
   *  @param dogId Database column DOG_ID SqlType(INT)
   *  @param ownerId Database column OWNER_ID SqlType(INT)
   *  @param action Database column ACTION SqlType(VARCHAR), Length(3,true)
   *  @param comment Database column COMMENT SqlType(TEXT) */
  case class ArchiveLogRow(id: Int, date: java.sql.Timestamp, userId: Int, dogId: Int, ownerId: Int, action: String, comment: String)
  /** GetResult implicit for fetching ArchiveLogRow objects using plain SQL queries */
  implicit def GetResultArchiveLogRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp], e2: GR[String]): GR[ArchiveLogRow] = GR{
    prs => import prs._
    ArchiveLogRow.tupled((<<[Int], <<[java.sql.Timestamp], <<[Int], <<[Int], <<[Int], <<[String], <<[String]))
  }
  /** Table description of table archive_log. Objects of this class serve as prototypes for rows in queries. */
  class ArchiveLog(_tableTag: Tag) extends profile.api.Table[ArchiveLogRow](_tableTag, Some("pets"), "archive_log") {
    def * = (id, date, userId, dogId, ownerId, action, comment) <> (ArchiveLogRow.tupled, ArchiveLogRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(date), Rep.Some(userId), Rep.Some(dogId), Rep.Some(ownerId), Rep.Some(action), Rep.Some(comment))).shaped.<>({r=>import r._; _1.map(_=> ArchiveLogRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column DATE SqlType(TIMESTAMP) */
    val date: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("DATE")
    /** Database column USER_ID SqlType(INT) */
    val userId: Rep[Int] = column[Int]("USER_ID")
    /** Database column DOG_ID SqlType(INT) */
    val dogId: Rep[Int] = column[Int]("DOG_ID")
    /** Database column OWNER_ID SqlType(INT) */
    val ownerId: Rep[Int] = column[Int]("OWNER_ID")
    /** Database column ACTION SqlType(VARCHAR), Length(3,true) */
    val action: Rep[String] = column[String]("ACTION", O.Length(3,varying=true))
    /** Database column COMMENT SqlType(TEXT) */
    val comment: Rep[String] = column[String]("COMMENT")

    /** Index over (dogId) (database name DOG_ID) */
    val index1 = index("DOG_ID", dogId)
  }
  /** Collection-like TableQuery object for table ArchiveLog */
  lazy val ArchiveLog = new TableQuery(tag => new ArchiveLog(tag))
}
