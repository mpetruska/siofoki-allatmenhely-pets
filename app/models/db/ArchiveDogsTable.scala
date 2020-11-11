package models.db
// AUTO-GENERATED Slick data model for table ArchiveDogs
trait ArchiveDogsTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  import slick.collection.heterogeneous._
  import slick.collection.heterogeneous.syntax._
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table ArchiveDogs
   *  @param dogId Database column DOG_ID SqlType(INT), PrimaryKey
   *  @param dogName Database column DOG_NAME SqlType(VARCHAR), Length(30,true)
   *  @param sex Database column SEX SqlType(VARCHAR), Length(3,true)
   *  @param breed Database column BREED SqlType(VARCHAR), Length(100,true)
   *  @param colour Database column COLOUR SqlType(VARCHAR), Length(30,true)
   *  @param hair Database column HAIR SqlType(VARCHAR), Length(3,true)
   *  @param bDate Database column B_DATE SqlType(DATE)
   *  @param estimated Database column ESTIMATED SqlType(BIT)
   *  @param marm Database column MARM SqlType(INT)
   *  @param chip Database column CHIP SqlType(VARCHAR), Length(30,true)
   *  @param bookNbr Database column BOOK_NBR SqlType(VARCHAR), Length(20,true)
   *  @param befPlace Database column BEF_PLACE SqlType(VARCHAR), Length(30,true)
   *  @param befDate Database column BEF_DATE SqlType(DATE)
   *  @param befCond Database column BEF_COND SqlType(TEXT)
   *  @param charact Database column CHARACT SqlType(TEXT)
   *  @param ivDate Database column IV_DATE SqlType(DATE)
   *  @param iv Database column IV SqlType(BIT), Default(false)
   *  @param status Database column STATUS SqlType(VARCHAR), Length(3,true)
   *  @param ownerId Database column OWNER_ID SqlType(INT)
   *  @param adoptDate Database column ADOPT_DATE SqlType(DATE)
   *  @param dDate Database column D_DATE SqlType(DATE)
   *  @param comment Database column COMMENT SqlType(TEXT)
   *  @param web Database column WEB SqlType(VARCHAR), Length(100,true) */
  case class ArchiveDogsRow(dogId: Int, dogName: String, sex: String, breed: String, colour: String, hair: String, bDate: java.sql.Date, estimated: Boolean, marm: Int, chip: String, bookNbr: String, befPlace: String, befDate: java.sql.Date, befCond: String, charact: String, ivDate: java.sql.Date, iv: Boolean = false, status: String, ownerId: Int, adoptDate: java.sql.Date, dDate: java.sql.Date, comment: String, web: String)
  /** GetResult implicit for fetching ArchiveDogsRow objects using plain SQL queries */
  implicit def GetResultArchiveDogsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Date], e3: GR[Boolean]): GR[ArchiveDogsRow] = GR{
    prs => import prs._
    ArchiveDogsRow(<<[Int], <<[String], <<[String], <<[String], <<[String], <<[String], <<[java.sql.Date], <<[Boolean], <<[Int], <<[String], <<[String], <<[String], <<[java.sql.Date], <<[String], <<[String], <<[java.sql.Date], <<[Boolean], <<[String], <<[Int], <<[java.sql.Date], <<[java.sql.Date], <<[String], <<[String])
  }
  /** Table description of table archive_dogs. Objects of this class serve as prototypes for rows in queries. */
  class ArchiveDogs(_tableTag: Tag) extends profile.api.Table[ArchiveDogsRow](_tableTag, Some("pets"), "archive_dogs") {
    def * = (dogId :: dogName :: sex :: breed :: colour :: hair :: bDate :: estimated :: marm :: chip :: bookNbr :: befPlace :: befDate :: befCond :: charact :: ivDate :: iv :: status :: ownerId :: adoptDate :: dDate :: comment :: web :: HNil).mapTo[ArchiveDogsRow]
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(dogId) :: Rep.Some(dogName) :: Rep.Some(sex) :: Rep.Some(breed) :: Rep.Some(colour) :: Rep.Some(hair) :: Rep.Some(bDate) :: Rep.Some(estimated) :: Rep.Some(marm) :: Rep.Some(chip) :: Rep.Some(bookNbr) :: Rep.Some(befPlace) :: Rep.Some(befDate) :: Rep.Some(befCond) :: Rep.Some(charact) :: Rep.Some(ivDate) :: Rep.Some(iv) :: Rep.Some(status) :: Rep.Some(ownerId) :: Rep.Some(adoptDate) :: Rep.Some(dDate) :: Rep.Some(comment) :: Rep.Some(web) :: HNil).shaped.<>(r => ArchiveDogsRow(r(0).asInstanceOf[Option[Int]].get, r(1).asInstanceOf[Option[String]].get, r(2).asInstanceOf[Option[String]].get, r(3).asInstanceOf[Option[String]].get, r(4).asInstanceOf[Option[String]].get, r(5).asInstanceOf[Option[String]].get, r(6).asInstanceOf[Option[java.sql.Date]].get, r(7).asInstanceOf[Option[Boolean]].get, r(8).asInstanceOf[Option[Int]].get, r(9).asInstanceOf[Option[String]].get, r(10).asInstanceOf[Option[String]].get, r(11).asInstanceOf[Option[String]].get, r(12).asInstanceOf[Option[java.sql.Date]].get, r(13).asInstanceOf[Option[String]].get, r(14).asInstanceOf[Option[String]].get, r(15).asInstanceOf[Option[java.sql.Date]].get, r(16).asInstanceOf[Option[Boolean]].get, r(17).asInstanceOf[Option[String]].get, r(18).asInstanceOf[Option[Int]].get, r(19).asInstanceOf[Option[java.sql.Date]].get, r(20).asInstanceOf[Option[java.sql.Date]].get, r(21).asInstanceOf[Option[String]].get, r(22).asInstanceOf[Option[String]].get), (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column DOG_ID SqlType(INT), PrimaryKey */
    val dogId: Rep[Int] = column[Int]("DOG_ID", O.PrimaryKey)
    /** Database column DOG_NAME SqlType(VARCHAR), Length(30,true) */
    val dogName: Rep[String] = column[String]("DOG_NAME", O.Length(30,varying=true))
    /** Database column SEX SqlType(VARCHAR), Length(3,true) */
    val sex: Rep[String] = column[String]("SEX", O.Length(3,varying=true))
    /** Database column BREED SqlType(VARCHAR), Length(100,true) */
    val breed: Rep[String] = column[String]("BREED", O.Length(100,varying=true))
    /** Database column COLOUR SqlType(VARCHAR), Length(30,true) */
    val colour: Rep[String] = column[String]("COLOUR", O.Length(30,varying=true))
    /** Database column HAIR SqlType(VARCHAR), Length(3,true) */
    val hair: Rep[String] = column[String]("HAIR", O.Length(3,varying=true))
    /** Database column B_DATE SqlType(DATE) */
    val bDate: Rep[java.sql.Date] = column[java.sql.Date]("B_DATE")
    /** Database column ESTIMATED SqlType(BIT) */
    val estimated: Rep[Boolean] = column[Boolean]("ESTIMATED")
    /** Database column MARM SqlType(INT) */
    val marm: Rep[Int] = column[Int]("MARM")
    /** Database column CHIP SqlType(VARCHAR), Length(30,true) */
    val chip: Rep[String] = column[String]("CHIP", O.Length(30,varying=true))
    /** Database column BOOK_NBR SqlType(VARCHAR), Length(20,true) */
    val bookNbr: Rep[String] = column[String]("BOOK_NBR", O.Length(20,varying=true))
    /** Database column BEF_PLACE SqlType(VARCHAR), Length(30,true) */
    val befPlace: Rep[String] = column[String]("BEF_PLACE", O.Length(30,varying=true))
    /** Database column BEF_DATE SqlType(DATE) */
    val befDate: Rep[java.sql.Date] = column[java.sql.Date]("BEF_DATE")
    /** Database column BEF_COND SqlType(TEXT) */
    val befCond: Rep[String] = column[String]("BEF_COND")
    /** Database column CHARACT SqlType(TEXT) */
    val charact: Rep[String] = column[String]("CHARACT")
    /** Database column IV_DATE SqlType(DATE) */
    val ivDate: Rep[java.sql.Date] = column[java.sql.Date]("IV_DATE")
    /** Database column IV SqlType(BIT), Default(false) */
    val iv: Rep[Boolean] = column[Boolean]("IV", O.Default(false))
    /** Database column STATUS SqlType(VARCHAR), Length(3,true) */
    val status: Rep[String] = column[String]("STATUS", O.Length(3,varying=true))
    /** Database column OWNER_ID SqlType(INT) */
    val ownerId: Rep[Int] = column[Int]("OWNER_ID")
    /** Database column ADOPT_DATE SqlType(DATE) */
    val adoptDate: Rep[java.sql.Date] = column[java.sql.Date]("ADOPT_DATE")
    /** Database column D_DATE SqlType(DATE) */
    val dDate: Rep[java.sql.Date] = column[java.sql.Date]("D_DATE")
    /** Database column COMMENT SqlType(TEXT) */
    val comment: Rep[String] = column[String]("COMMENT")
    /** Database column WEB SqlType(VARCHAR), Length(100,true) */
    val web: Rep[String] = column[String]("WEB", O.Length(100,varying=true))

    /** Index over (dogName) (database name DOG_NAME) */
    val index1 = index("DOG_NAME", dogName :: HNil)
    /** Index over (hair) (database name HAIR) */
    val index2 = index("HAIR", hair :: HNil)
    /** Index over (marm) (database name MARM) */
    val index3 = index("MARM", marm :: HNil)
    /** Index over (sex) (database name SEX) */
    val index4 = index("SEX", sex :: HNil)
  }
  /** Collection-like TableQuery object for table ArchiveDogs */
  lazy val ArchiveDogs = new TableQuery(tag => new ArchiveDogs(tag))
}
