package bao.ho.service.woker

import java.util.Date

import scala.concurrent.Future

trait WorkerService {
  def importHistoricData(currency: String, from: Date, to: Date): Future[String]
}
