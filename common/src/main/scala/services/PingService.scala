package services

import scala.concurrent.Future

class PingService {

  def ping(a: Unit): Future[Either[String, String]] = {
    val res: Future[Either[String, String]] = Future.successful(
      Right("pong")
    )
    res
  }

}
