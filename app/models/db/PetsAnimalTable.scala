package models.db
// AUTO-GENERATED Slick data model for table PetsAnimal
trait PetsAnimalTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table PetsAnimal
   *  @param id Database column id SqlType(CHAR), PrimaryKey, Length(36,false)
   *  @param name Database column name SqlType(TEXT)
   *  @param sex Database column sex SqlType(TEXT), Default(None)
   *  @param breed Database column breed SqlType(TEXT), Default(None)
   *  @param color Database column color SqlType(TEXT), Default(None)
   *  @param hairStyle Database column hair_style SqlType(TEXT), Default(None)
   *  @param isBirthDateEstimated Database column is_birth_date_estimated SqlType(BIT)
   *  @param dateOfBirth Database column date_of_birth SqlType(DATE), Default(None)
   *  @param heightAtWithers Database column height_at_withers SqlType(INT), Default(None)
   *  @param chip Database column chip SqlType(TEXT), Default(None)
   *  @param vaccinationBookNumber Database column vaccination_book_number SqlType(TEXT), Default(None)
   *  @param placeOfInclusion Database column place_of_inclusion SqlType(TEXT), Default(None)
   *  @param dateOfInclusion Database column date_of_inclusion SqlType(DATE), Default(None)
   *  @param contextOfInclusion Database column context_of_inclusion SqlType(TEXT), Default(None)
   *  @param character Database column character SqlType(TEXT), Default(None)
   *  @param isNeutered Database column is_neutered SqlType(BIT)
   *  @param dateOfNautering Database column date_of_nautering SqlType(DATE), Default(None)
   *  @param dateOfAdoption Database column date_of_adoption SqlType(DATE), Default(None)
   *  @param dateOfDeath Database column date_of_death SqlType(DATE), Default(None)
   *  @param status Database column status SqlType(TEXT), Default(None)
   *  @param ownerId Database column owner_id SqlType(CHAR), Length(36,false), Default(None)
   *  @param comment Database column comment SqlType(TEXT), Default(None) */
  case class PetsAnimalRow(id: String, name: String, sex: Option[String] = None, breed: Option[String] = None, color: Option[String] = None, hairStyle: Option[String] = None, isBirthDateEstimated: Boolean, dateOfBirth: Option[java.sql.Date] = None, heightAtWithers: Option[Int] = None, chip: Option[String] = None, vaccinationBookNumber: Option[String] = None, placeOfInclusion: Option[String] = None, dateOfInclusion: Option[java.sql.Date] = None, contextOfInclusion: Option[String] = None, character: Option[String] = None, isNeutered: Boolean, dateOfNautering: Option[java.sql.Date] = None, dateOfAdoption: Option[java.sql.Date] = None, dateOfDeath: Option[java.sql.Date] = None, status: Option[String] = None, ownerId: Option[String] = None, comment: Option[String] = None)
  /** GetResult implicit for fetching PetsAnimalRow objects using plain SQL queries */
  implicit def GetResultPetsAnimalRow(implicit e0: GR[String], e1: GR[Option[String]], e2: GR[Boolean], e3: GR[Option[java.sql.Date]], e4: GR[Option[Int]]): GR[PetsAnimalRow] = GR{
    prs => import prs._
    PetsAnimalRow.tupled((<<[String], <<[String], <<?[String], <<?[String], <<?[String], <<?[String], <<[Boolean], <<?[java.sql.Date], <<?[Int], <<?[String], <<?[String], <<?[String], <<?[java.sql.Date], <<?[String], <<?[String], <<[Boolean], <<?[java.sql.Date], <<?[java.sql.Date], <<?[java.sql.Date], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table pets_animal. Objects of this class serve as prototypes for rows in queries. */
  class PetsAnimal(_tableTag: Tag) extends profile.api.Table[PetsAnimalRow](_tableTag, Some("pets"), "pets_animal") {
    def * = (id, name, sex, breed, color, hairStyle, isBirthDateEstimated, dateOfBirth, heightAtWithers, chip, vaccinationBookNumber, placeOfInclusion, dateOfInclusion, contextOfInclusion, character, isNeutered, dateOfNautering, dateOfAdoption, dateOfDeath, status, ownerId, comment) <> (PetsAnimalRow.tupled, PetsAnimalRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(name), sex, breed, color, hairStyle, Rep.Some(isBirthDateEstimated), dateOfBirth, heightAtWithers, chip, vaccinationBookNumber, placeOfInclusion, dateOfInclusion, contextOfInclusion, character, Rep.Some(isNeutered), dateOfNautering, dateOfAdoption, dateOfDeath, status, ownerId, comment)).shaped.<>({r=>import r._; _1.map(_=> PetsAnimalRow.tupled((_1.get, _2.get, _3, _4, _5, _6, _7.get, _8, _9, _10, _11, _12, _13, _14, _15, _16.get, _17, _18, _19, _20, _21, _22)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(CHAR), PrimaryKey, Length(36,false) */
    val id: Rep[String] = column[String]("id", O.PrimaryKey, O.Length(36,varying=false))
    /** Database column name SqlType(TEXT) */
    val name: Rep[String] = column[String]("name")
    /** Database column sex SqlType(TEXT), Default(None) */
    val sex: Rep[Option[String]] = column[Option[String]]("sex", O.Default(None))
    /** Database column breed SqlType(TEXT), Default(None) */
    val breed: Rep[Option[String]] = column[Option[String]]("breed", O.Default(None))
    /** Database column color SqlType(TEXT), Default(None) */
    val color: Rep[Option[String]] = column[Option[String]]("color", O.Default(None))
    /** Database column hair_style SqlType(TEXT), Default(None) */
    val hairStyle: Rep[Option[String]] = column[Option[String]]("hair_style", O.Default(None))
    /** Database column is_birth_date_estimated SqlType(BIT) */
    val isBirthDateEstimated: Rep[Boolean] = column[Boolean]("is_birth_date_estimated")
    /** Database column date_of_birth SqlType(DATE), Default(None) */
    val dateOfBirth: Rep[Option[java.sql.Date]] = column[Option[java.sql.Date]]("date_of_birth", O.Default(None))
    /** Database column height_at_withers SqlType(INT), Default(None) */
    val heightAtWithers: Rep[Option[Int]] = column[Option[Int]]("height_at_withers", O.Default(None))
    /** Database column chip SqlType(TEXT), Default(None) */
    val chip: Rep[Option[String]] = column[Option[String]]("chip", O.Default(None))
    /** Database column vaccination_book_number SqlType(TEXT), Default(None) */
    val vaccinationBookNumber: Rep[Option[String]] = column[Option[String]]("vaccination_book_number", O.Default(None))
    /** Database column place_of_inclusion SqlType(TEXT), Default(None) */
    val placeOfInclusion: Rep[Option[String]] = column[Option[String]]("place_of_inclusion", O.Default(None))
    /** Database column date_of_inclusion SqlType(DATE), Default(None) */
    val dateOfInclusion: Rep[Option[java.sql.Date]] = column[Option[java.sql.Date]]("date_of_inclusion", O.Default(None))
    /** Database column context_of_inclusion SqlType(TEXT), Default(None) */
    val contextOfInclusion: Rep[Option[String]] = column[Option[String]]("context_of_inclusion", O.Default(None))
    /** Database column character SqlType(TEXT), Default(None) */
    val character: Rep[Option[String]] = column[Option[String]]("character", O.Default(None))
    /** Database column is_neutered SqlType(BIT) */
    val isNeutered: Rep[Boolean] = column[Boolean]("is_neutered")
    /** Database column date_of_nautering SqlType(DATE), Default(None) */
    val dateOfNautering: Rep[Option[java.sql.Date]] = column[Option[java.sql.Date]]("date_of_nautering", O.Default(None))
    /** Database column date_of_adoption SqlType(DATE), Default(None) */
    val dateOfAdoption: Rep[Option[java.sql.Date]] = column[Option[java.sql.Date]]("date_of_adoption", O.Default(None))
    /** Database column date_of_death SqlType(DATE), Default(None) */
    val dateOfDeath: Rep[Option[java.sql.Date]] = column[Option[java.sql.Date]]("date_of_death", O.Default(None))
    /** Database column status SqlType(TEXT), Default(None) */
    val status: Rep[Option[String]] = column[Option[String]]("status", O.Default(None))
    /** Database column owner_id SqlType(CHAR), Length(36,false), Default(None) */
    val ownerId: Rep[Option[String]] = column[Option[String]]("owner_id", O.Length(36,varying=false), O.Default(None))
    /** Database column comment SqlType(TEXT), Default(None) */
    val comment: Rep[Option[String]] = column[Option[String]]("comment", O.Default(None))

    /** Foreign key referencing PetsOwner (database name pets_animal_ibfk_1) */
    lazy val petsOwnerFk = foreignKey("pets_animal_ibfk_1", ownerId, PetsOwner)(r => Rep.Some(r.id), onUpdate=ForeignKeyAction.Restrict, onDelete=ForeignKeyAction.Restrict)
  }
  /** Collection-like TableQuery object for table PetsAnimal */
  lazy val PetsAnimal = new TableQuery(tag => new PetsAnimal(tag))
}
