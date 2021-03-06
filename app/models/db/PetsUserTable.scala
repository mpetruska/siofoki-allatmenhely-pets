package models.db
// AUTO-GENERATED Slick data model for table PetsUser
trait PetsUserTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table PetsUser
   *  @param id Database column id SqlType(CHAR), PrimaryKey, Length(36,false)
   *  @param username Database column username SqlType(VARCHAR), Length(200,true)
   *  @param fullName Database column full_name SqlType(TEXT), Default(None)
   *  @param password Database column password SqlType(TEXT), Default(None)
   *  @param isAdmin Database column is_admin SqlType(BIT)
   *  @param isEnabled Database column is_enabled SqlType(BIT) */
  case class PetsUserRow(id: String, username: String, fullName: Option[String] = None, password: Option[String] = None, isAdmin: Boolean, isEnabled: Boolean)
  /** GetResult implicit for fetching PetsUserRow objects using plain SQL queries */
  implicit def GetResultPetsUserRow(implicit e0: GR[String], e1: GR[Option[String]], e2: GR[Boolean]): GR[PetsUserRow] = GR{
    prs => import prs._
    PetsUserRow.tupled((<<[String], <<[String], <<?[String], <<?[String], <<[Boolean], <<[Boolean]))
  }
  /** Table description of table pets_user. Objects of this class serve as prototypes for rows in queries. */
  class PetsUser(_tableTag: Tag) extends profile.api.Table[PetsUserRow](_tableTag, Some("pets"), "pets_user") {
    def * = (id, username, fullName, password, isAdmin, isEnabled) <> (PetsUserRow.tupled, PetsUserRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(username), fullName, password, Rep.Some(isAdmin), Rep.Some(isEnabled))).shaped.<>({r=>import r._; _1.map(_=> PetsUserRow.tupled((_1.get, _2.get, _3, _4, _5.get, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(CHAR), PrimaryKey, Length(36,false) */
    val id: Rep[String] = column[String]("id", O.PrimaryKey, O.Length(36,varying=false))
    /** Database column username SqlType(VARCHAR), Length(200,true) */
    val username: Rep[String] = column[String]("username", O.Length(200,varying=true))
    /** Database column full_name SqlType(TEXT), Default(None) */
    val fullName: Rep[Option[String]] = column[Option[String]]("full_name", O.Default(None))
    /** Database column password SqlType(TEXT), Default(None) */
    val password: Rep[Option[String]] = column[Option[String]]("password", O.Default(None))
    /** Database column is_admin SqlType(BIT) */
    val isAdmin: Rep[Boolean] = column[Boolean]("is_admin")
    /** Database column is_enabled SqlType(BIT) */
    val isEnabled: Rep[Boolean] = column[Boolean]("is_enabled")

    /** Uniqueness Index over (username) (database name uq_user_username) */
    val index1 = index("uq_user_username", username, unique=true)
  }
  /** Collection-like TableQuery object for table PetsUser */
  lazy val PetsUser = new TableQuery(tag => new PetsUser(tag))
}
