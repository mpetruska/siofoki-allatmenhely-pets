package models.db
// AUTO-GENERATED Slick data model for table PetsImage
trait PetsImageTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table PetsImage
   *  @param id Database column id SqlType(CHAR), PrimaryKey, Length(36,false)
   *  @param animalId Database column animal_id SqlType(CHAR), Length(36,false)
   *  @param filename Database column filename SqlType(TEXT), Default(None)
   *  @param title Database column title SqlType(TEXT), Default(None)
   *  @param description Database column description SqlType(TEXT), Default(None)
   *  @param imageData Database column image_data SqlType(BLOB), Default(None) */
  case class PetsImageRow(id: String, animalId: String, filename: Option[String] = None, title: Option[String] = None, description: Option[String] = None, imageData: Option[java.sql.Blob] = None)
  /** GetResult implicit for fetching PetsImageRow objects using plain SQL queries */
  implicit def GetResultPetsImageRow(implicit e0: GR[String], e1: GR[Option[String]], e2: GR[Option[java.sql.Blob]]): GR[PetsImageRow] = GR{
    prs => import prs._
    PetsImageRow.tupled((<<[String], <<[String], <<?[String], <<?[String], <<?[String], <<?[java.sql.Blob]))
  }
  /** Table description of table pets_image. Objects of this class serve as prototypes for rows in queries. */
  class PetsImage(_tableTag: Tag) extends profile.api.Table[PetsImageRow](_tableTag, Some("pets"), "pets_image") {
    def * = (id, animalId, filename, title, description, imageData) <> (PetsImageRow.tupled, PetsImageRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(animalId), filename, title, description, imageData)).shaped.<>({r=>import r._; _1.map(_=> PetsImageRow.tupled((_1.get, _2.get, _3, _4, _5, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(CHAR), PrimaryKey, Length(36,false) */
    val id: Rep[String] = column[String]("id", O.PrimaryKey, O.Length(36,varying=false))
    /** Database column animal_id SqlType(CHAR), Length(36,false) */
    val animalId: Rep[String] = column[String]("animal_id", O.Length(36,varying=false))
    /** Database column filename SqlType(TEXT), Default(None) */
    val filename: Rep[Option[String]] = column[Option[String]]("filename", O.Default(None))
    /** Database column title SqlType(TEXT), Default(None) */
    val title: Rep[Option[String]] = column[Option[String]]("title", O.Default(None))
    /** Database column description SqlType(TEXT), Default(None) */
    val description: Rep[Option[String]] = column[Option[String]]("description", O.Default(None))
    /** Database column image_data SqlType(BLOB), Default(None) */
    val imageData: Rep[Option[java.sql.Blob]] = column[Option[java.sql.Blob]]("image_data", O.Default(None))

    /** Foreign key referencing PetsAnimal (database name pets_image_ibfk_1) */
    lazy val petsAnimalFk = foreignKey("pets_image_ibfk_1", animalId, PetsAnimal)(r => r.id, onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
  }
  /** Collection-like TableQuery object for table PetsImage */
  lazy val PetsImage = new TableQuery(tag => new PetsImage(tag))
}
