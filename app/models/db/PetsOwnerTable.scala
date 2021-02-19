package models.db
// AUTO-GENERATED Slick data model for table PetsOwner
trait PetsOwnerTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table PetsOwner
   *  @param id Database column id SqlType(CHAR), PrimaryKey, Length(36,false)
   *  @param name Database column name SqlType(TEXT)
   *  @param address Database column address SqlType(TEXT), Default(None) */
  case class PetsOwnerRow(id: String, name: String, address: Option[String] = None)
  /** GetResult implicit for fetching PetsOwnerRow objects using plain SQL queries */
  implicit def GetResultPetsOwnerRow(implicit e0: GR[String], e1: GR[Option[String]]): GR[PetsOwnerRow] = GR{
    prs => import prs._
    PetsOwnerRow.tupled((<<[String], <<[String], <<?[String]))
  }
  /** Table description of table pets_owner. Objects of this class serve as prototypes for rows in queries. */
  class PetsOwner(_tableTag: Tag) extends profile.api.Table[PetsOwnerRow](_tableTag, Some("pets"), "pets_owner") {
    def * = (id, name, address) <> (PetsOwnerRow.tupled, PetsOwnerRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(name), address)).shaped.<>({r=>import r._; _1.map(_=> PetsOwnerRow.tupled((_1.get, _2.get, _3)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(CHAR), PrimaryKey, Length(36,false) */
    val id: Rep[String] = column[String]("id", O.PrimaryKey, O.Length(36,varying=false))
    /** Database column name SqlType(TEXT) */
    val name: Rep[String] = column[String]("name")
    /** Database column address SqlType(TEXT), Default(None) */
    val address: Rep[Option[String]] = column[Option[String]]("address", O.Default(None))
  }
  /** Collection-like TableQuery object for table PetsOwner */
  lazy val PetsOwner = new TableQuery(tag => new PetsOwner(tag))
}
