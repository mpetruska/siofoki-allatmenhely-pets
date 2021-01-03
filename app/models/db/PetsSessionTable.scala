package models.db
// AUTO-GENERATED Slick data model for table PetsSession
trait PetsSessionTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table PetsSession
   *  @param id Database column id SqlType(CHAR), PrimaryKey, Length(36,false)
   *  @param userId Database column user_id SqlType(CHAR), Length(36,false)
   *  @param createdUtc Database column created_utc SqlType(DATETIME)
   *  @param lastAccessedUtc Database column last_accessed_utc SqlType(DATETIME)
   *  @param isPermanent Database column is_permanent SqlType(BIT), Default(false) */
  case class PetsSessionRow(id: String, userId: String, createdUtc: java.sql.Timestamp, lastAccessedUtc: java.sql.Timestamp, isPermanent: Boolean = false)
  /** GetResult implicit for fetching PetsSessionRow objects using plain SQL queries */
  implicit def GetResultPetsSessionRow(implicit e0: GR[String], e1: GR[java.sql.Timestamp], e2: GR[Boolean]): GR[PetsSessionRow] = GR{
    prs => import prs._
    PetsSessionRow.tupled((<<[String], <<[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Boolean]))
  }
  /** Table description of table pets_session. Objects of this class serve as prototypes for rows in queries. */
  class PetsSession(_tableTag: Tag) extends profile.api.Table[PetsSessionRow](_tableTag, Some("pets"), "pets_session") {
    def * = (id, userId, createdUtc, lastAccessedUtc, isPermanent) <> (PetsSessionRow.tupled, PetsSessionRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(userId), Rep.Some(createdUtc), Rep.Some(lastAccessedUtc), Rep.Some(isPermanent))).shaped.<>({r=>import r._; _1.map(_=> PetsSessionRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(CHAR), PrimaryKey, Length(36,false) */
    val id: Rep[String] = column[String]("id", O.PrimaryKey, O.Length(36,varying=false))
    /** Database column user_id SqlType(CHAR), Length(36,false) */
    val userId: Rep[String] = column[String]("user_id", O.Length(36,varying=false))
    /** Database column created_utc SqlType(DATETIME) */
    val createdUtc: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_utc")
    /** Database column last_accessed_utc SqlType(DATETIME) */
    val lastAccessedUtc: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("last_accessed_utc")
    /** Database column is_permanent SqlType(BIT), Default(false) */
    val isPermanent: Rep[Boolean] = column[Boolean]("is_permanent", O.Default(false))

    /** Index over (isPermanent,lastAccessedUtc) (database name ix_session_last_accessed) */
    val index1 = index("ix_session_last_accessed", (isPermanent, lastAccessedUtc))
  }
  /** Collection-like TableQuery object for table PetsSession */
  lazy val PetsSession = new TableQuery(tag => new PetsSession(tag))
}
