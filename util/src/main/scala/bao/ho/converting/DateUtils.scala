package bao.ho.converting

import java.text.SimpleDateFormat
import java.util.Date
//import java.time.Instant

object DateUtils {
  val formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ssZ")

  /**
    * Convert a Date to Epoch timestamp
    *
    * @param date
    * @return
    */
  def convertDateToEpoch(date: Date): Long = date.getTime

  /**
    * Convert a Epoch timestamp to Date
    *
    * @param epoch
    * @return
    */
  def convertEpochToDate(epoch: Long): Date = new Date(epoch)

  /**
    * Convert a Date string to Date with yyyy-MM-dd hh:mm:ssZ format
    *
    * @param date
    * @return
    */
  def convertStringToDate(date: String): Date = {
    formatter.parse(date)
  }

}
