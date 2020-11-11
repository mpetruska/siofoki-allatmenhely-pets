package models.db
// AUTO-GENERATED Slick data model for table Users
trait UsersTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Users
   *  @param userId Database column USER_ID SqlType(INT), AutoInc, PrimaryKey
   *  @param name Database column NAME SqlType(VARCHAR), Length(40,true)
   *  @param email Database column EMAIL SqlType(VARCHAR), Length(40,true)
   *  @param password Database column PASSWORD SqlType(VARCHAR), Length(35,true)
   *  @param rights Database column RIGHTS SqlType(INT)
   *  @param lastLogin Database column LAST_LOGIN SqlType(VARCHAR), Length(20,true)
   *  @param ipAddress Database column IP_ADDRESS SqlType(VARCHAR), Length(20,true) */
  case class UsersRow(userId: Int, name: String, email: String, password: String, rights: Int, lastLogin: String, ipAddress: String)
  /** GetResult implicit for fetching UsersRow objects using plain SQL queries */
  implicit def GetResultUsersRow(implicit e0: GR[Int], e1: GR[String]): GR[UsersRow] = GR{
    prs => import prs._
    UsersRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[Int], <<[String], <<[String]))
  }
  /** Table description of table users. Objects of this class serve as prototypes for rows in queries. */
  class Users(_tableTag: Tag) extends profile.api.Table[UsersRow](_tableTag, Some("pets"), "users") {
    def * = (userId, name, email, password, rights, lastLogin, ipAddress) <> (UsersRow.tupled, UsersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(userId), Rep.Some(name), Rep.Some(email), Rep.Some(password), Rep.Some(rights), Rep.Some(lastLogin), Rep.Some(ipAddress))).shaped.<>({r=>import r._; _1.map(_=> UsersRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column USER_ID SqlType(INT), AutoInc, PrimaryKey */
    val userId: Rep[Int] = column[Int]("USER_ID", O.AutoInc, O.PrimaryKey)
    /** Database column NAME SqlType(VARCHAR), Length(40,true) */
    val name: Rep[String] = column[String]("NAME", O.Length(40,varying=true))
    /** Database column EMAIL SqlType(VARCHAR), Length(40,true) */
    val email: Rep[String] = column[String]("EMAIL", O.Length(40,varying=true))
    /** Database column PASSWORD SqlType(VARCHAR), Length(35,true) */
    val password: Rep[String] = column[String]("PASSWORD", O.Length(35,varying=true))
    /** Database column RIGHTS SqlType(INT) */
    val rights: Rep[Int] = column[Int]("RIGHTS")
    /** Database column LAST_LOGIN SqlType(VARCHAR), Length(20,true) */
    val lastLogin: Rep[String] = column[String]("LAST_LOGIN", O.Length(20,varying=true))
    /** Database column IP_ADDRESS SqlType(VARCHAR), Length(20,true) */
    val ipAddress: Rep[String] = column[String]("IP_ADDRESS", O.Length(20,varying=true))
  }
  /** Collection-like TableQuery object for table Users */
  lazy val Users = new TableQuery(tag => new Users(tag))
}
