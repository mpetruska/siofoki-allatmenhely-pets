package models.db
// AUTO-GENERATED Slick data model for table Data
trait DataTable {

  self:Tables  =>

  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}
  /** Entity class storing rows of table Data
   *  @param id Database column ID SqlType(VARCHAR), PrimaryKey, Length(10,true)
   *  @param dataHu Database column DATA_HU SqlType(VARCHAR), Length(50,true)
   *  @param dataEn Database column DATA_EN SqlType(VARCHAR), Length(50,true)
   *  @param dataDe Database column DATA_DE SqlType(VARCHAR), Length(50,true) */
  case class DataRow(id: String, dataHu: String, dataEn: String, dataDe: String)
  /** GetResult implicit for fetching DataRow objects using plain SQL queries */
  implicit def GetResultDataRow(implicit e0: GR[String]): GR[DataRow] = GR{
    prs => import prs._
    DataRow.tupled((<<[String], <<[String], <<[String], <<[String]))
  }
  /** Table description of table data. Objects of this class serve as prototypes for rows in queries. */
  class Data(_tableTag: Tag) extends profile.api.Table[DataRow](_tableTag, Some("pets"), "data") {
    def * = (id, dataHu, dataEn, dataDe) <> (DataRow.tupled, DataRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(dataHu), Rep.Some(dataEn), Rep.Some(dataDe))).shaped.<>({r=>import r._; _1.map(_=> DataRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(VARCHAR), PrimaryKey, Length(10,true) */
    val id: Rep[String] = column[String]("ID", O.PrimaryKey, O.Length(10,varying=true))
    /** Database column DATA_HU SqlType(VARCHAR), Length(50,true) */
    val dataHu: Rep[String] = column[String]("DATA_HU", O.Length(50,varying=true))
    /** Database column DATA_EN SqlType(VARCHAR), Length(50,true) */
    val dataEn: Rep[String] = column[String]("DATA_EN", O.Length(50,varying=true))
    /** Database column DATA_DE SqlType(VARCHAR), Length(50,true) */
    val dataDe: Rep[String] = column[String]("DATA_DE", O.Length(50,varying=true))
  }
  /** Collection-like TableQuery object for table Data */
  lazy val Data = new TableQuery(tag => new Data(tag))
}
