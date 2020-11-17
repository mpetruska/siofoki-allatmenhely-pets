package models.db
// AUTO-GENERATED Slick data model for table ArchiveMedical
trait ArchiveMedicalTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table ArchiveMedical
   *  @param dogId Database column DOG_ID SqlType(INT)
   *  @param date Database column DATE SqlType(DATE)
   *  @param `type` Database column TYPE SqlType(VARCHAR), Length(2,true)
   *  @param mDesc Database column M_DESC SqlType(TEXT)
   *  @param doctor Database column DOCTOR SqlType(VARCHAR), Length(25,true) */
  case class ArchiveMedicalRow(dogId: Int, date: Option[java.sql.Date], `type`: String, mDesc: String, doctor: String)
  /** GetResult implicit for fetching ArchiveMedicalRow objects using plain SQL queries */
  implicit def GetResultArchiveMedicalRow(implicit e0: GR[Int], e1: GR[Option[java.sql.Date]], e2: GR[String]): GR[ArchiveMedicalRow] = GR{
    prs => import prs._
    ArchiveMedicalRow.tupled((<<[Int], <<[Option[java.sql.Date]], <<[String], <<[String], <<[String]))
  }
  /** Table description of table archive_medical. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class ArchiveMedical(_tableTag: Tag) extends profile.api.Table[ArchiveMedicalRow](_tableTag, Some("pets"), "archive_medical") {
    def * = (dogId, date, `type`, mDesc, doctor) <> (ArchiveMedicalRow.tupled, ArchiveMedicalRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(dogId), Rep.Some(date), Rep.Some(`type`), Rep.Some(mDesc), Rep.Some(doctor))).shaped.<>({r=>import r._; _1.map(_=> ArchiveMedicalRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column DOG_ID SqlType(INT) */
    val dogId: Rep[Int] = column[Int]("DOG_ID")
    /** Database column DATE SqlType(DATE) */
    val date: Rep[Option[java.sql.Date]] = column[Option[java.sql.Date]]("DATE")
    /** Database column TYPE SqlType(VARCHAR), Length(2,true)
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `type`: Rep[String] = column[String]("TYPE", O.Length(2,varying=true))
    /** Database column M_DESC SqlType(TEXT) */
    val mDesc: Rep[String] = column[String]("M_DESC")
    /** Database column DOCTOR SqlType(VARCHAR), Length(25,true) */
    val doctor: Rep[String] = column[String]("DOCTOR", O.Length(25,varying=true))

    /** Index over (dogId) (database name DOG_ID) */
    val index1 = index("DOG_ID", dogId)
  }
  /** Collection-like TableQuery object for table ArchiveMedical */
  lazy val ArchiveMedical = new TableQuery(tag => new ArchiveMedical(tag))
}
