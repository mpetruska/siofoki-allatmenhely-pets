package models.db
// AUTO-GENERATED Slick data model for table Owner
trait OwnerTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Owner
   *  @param ownerId Database column OWNER_ID SqlType(INT), AutoInc, PrimaryKey
   *  @param name Database column NAME SqlType(VARCHAR), Length(30,true)
   *  @param szigsz Database column SZIGSZ SqlType(VARCHAR), Length(10,true)
   *  @param pCode Database column P_CODE SqlType(INT)
   *  @param city Database column CITY SqlType(VARCHAR), Length(20,true)
   *  @param address Database column ADDRESS SqlType(VARCHAR), Length(40,true)
   *  @param email Database column EMAIL SqlType(VARCHAR), Length(40,true)
   *  @param tel Database column TEL SqlType(VARCHAR), Length(20,true)
   *  @param comment Database column COMMENT SqlType(TEXT) */
  case class OwnerRow(ownerId: Int, name: String, szigsz: String, pCode: Int, city: String, address: String, email: String, tel: String, comment: String)
  /** GetResult implicit for fetching OwnerRow objects using plain SQL queries */
  implicit def GetResultOwnerRow(implicit e0: GR[Int], e1: GR[String]): GR[OwnerRow] = GR{
    prs => import prs._
    OwnerRow.tupled((<<[Int], <<[String], <<[String], <<[Int], <<[String], <<[String], <<[String], <<[String], <<[String]))
  }
  /** Table description of table owner. Objects of this class serve as prototypes for rows in queries. */
  class Owner(_tableTag: Tag) extends profile.api.Table[OwnerRow](_tableTag, Some("pets"), "owner") {
    def * = (ownerId, name, szigsz, pCode, city, address, email, tel, comment) <> (OwnerRow.tupled, OwnerRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(ownerId), Rep.Some(name), Rep.Some(szigsz), Rep.Some(pCode), Rep.Some(city), Rep.Some(address), Rep.Some(email), Rep.Some(tel), Rep.Some(comment))).shaped.<>({r=>import r._; _1.map(_=> OwnerRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column OWNER_ID SqlType(INT), AutoInc, PrimaryKey */
    val ownerId: Rep[Int] = column[Int]("OWNER_ID", O.AutoInc, O.PrimaryKey)
    /** Database column NAME SqlType(VARCHAR), Length(30,true) */
    val name: Rep[String] = column[String]("NAME", O.Length(30,varying=true))
    /** Database column SZIGSZ SqlType(VARCHAR), Length(10,true) */
    val szigsz: Rep[String] = column[String]("SZIGSZ", O.Length(10,varying=true))
    /** Database column P_CODE SqlType(INT) */
    val pCode: Rep[Int] = column[Int]("P_CODE")
    /** Database column CITY SqlType(VARCHAR), Length(20,true) */
    val city: Rep[String] = column[String]("CITY", O.Length(20,varying=true))
    /** Database column ADDRESS SqlType(VARCHAR), Length(40,true) */
    val address: Rep[String] = column[String]("ADDRESS", O.Length(40,varying=true))
    /** Database column EMAIL SqlType(VARCHAR), Length(40,true) */
    val email: Rep[String] = column[String]("EMAIL", O.Length(40,varying=true))
    /** Database column TEL SqlType(VARCHAR), Length(20,true) */
    val tel: Rep[String] = column[String]("TEL", O.Length(20,varying=true))
    /** Database column COMMENT SqlType(TEXT) */
    val comment: Rep[String] = column[String]("COMMENT")
  }
  /** Collection-like TableQuery object for table Owner */
  lazy val Owner = new TableQuery(tag => new Owner(tag))
}
