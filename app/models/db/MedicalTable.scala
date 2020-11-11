package models.db
// AUTO-GENERATED Slick data model for table Medical
trait MedicalTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Medical
   *  @param dogId Database column DOG_ID SqlType(INT)
   *  @param date Database column DATE SqlType(DATE)
   *  @param `type` Database column TYPE SqlType(VARCHAR), Length(2,true)
   *  @param mDesc Database column M_DESC SqlType(TEXT)
   *  @param doctor Database column DOCTOR SqlType(VARCHAR), Length(25,true) */
  case class MedicalRow(dogId: Int, date: java.sql.Date, `type`: String, mDesc: String, doctor: String)
  /** GetResult implicit for fetching MedicalRow objects using plain SQL queries */
  implicit def GetResultMedicalRow(implicit e0: GR[Int], e1: GR[java.sql.Date], e2: GR[String]): GR[MedicalRow] = GR{
    prs => import prs._
    MedicalRow.tupled((<<[Int], <<[java.sql.Date], <<[String], <<[String], <<[String]))
  }
  /** Table description of table medical. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: type */
  class Medical(_tableTag: Tag) extends profile.api.Table[MedicalRow](_tableTag, Some("pets"), "medical") {
    def * = (dogId, date, `type`, mDesc, doctor) <> (MedicalRow.tupled, MedicalRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(dogId), Rep.Some(date), Rep.Some(`type`), Rep.Some(mDesc), Rep.Some(doctor))).shaped.<>({r=>import r._; _1.map(_=> MedicalRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column DOG_ID SqlType(INT) */
    val dogId: Rep[Int] = column[Int]("DOG_ID")
    /** Database column DATE SqlType(DATE) */
    val date: Rep[java.sql.Date] = column[java.sql.Date]("DATE")
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
  /** Collection-like TableQuery object for table Medical */
  lazy val Medical = new TableQuery(tag => new Medical(tag))
}
