package services

import com.demo.ServiceResponse
import com.demo.domain.Contact
import com.typesafe.scalalogging.LazyLogging
import repository.Repository

import scala.concurrent.{ExecutionContext, Future}

class ContactsService(contactsRepo: Repository[String, Contact])(implicit ec: ExecutionContext) extends LazyLogging {

  def getAll(country: Option[String]): Future[Either[ServiceResponse, Seq[Contact]]] = {
    logger.debug(s"getAll(), country = ${country.getOrElse("no country selected for filtering")}")
    contactsRepo
      .findAll()
      .map { contacts =>
        val res = country match {
          case None          => contacts
          case Some(country) => contacts.filter(_.country == country)
        }
        Right(res)
      }
      .recoverWith {
        case e: Throwable =>
          val errorMsg = s"Error when retrieving contacts, ${e.getMessage}"
          logger.error(errorMsg, e)
          Future.successful(
            Left(
              ServiceResponse(status = "Error", description = errorMsg)
            )
          )
      }
  }

  def addContact(token: Option[String], c: Contact): Future[Either[ServiceResponse, Contact]] = {
    logger.debug(s"addContact(), token = ${token.getOrElse("no token")}")
    contactsRepo
      .read(c.email)
      .flatMap {
        case Some(_) =>
          Future.successful(
            Left(
              ServiceResponse(status = "error", description = s"Contact already exists")
            )
          )
        case None =>
          contactsRepo
            .create(c)
            .map(Right(_))
            .recoverWith {
              case e: Throwable =>
                val errorMsg = s"Error when creating contact, ${e.getMessage}"
                logger.error(errorMsg, e)
                Future.successful(
                  Left(
                    ServiceResponse(status = "Error", description = errorMsg)
                  )
                )
            }
      }
  }

  def getContact(email: String): Future[Either[ServiceResponse, Contact]] = {
    logger.debug(s"getContact(), email = $email")
    contactsRepo
      .read(email)
      .map {
        case Some(c) => Right(c)
        case None =>
          Left(
            ServiceResponse(status = "error", description = s"Contact not found")
          )
      }
      .recoverWith {
        case e: Throwable =>
          val errorMsg = s"Error when retrieving contact, ${e.getMessage}"
          logger.error(errorMsg, e)
          Future.successful(
            Left(
              ServiceResponse(status = "Error", description = errorMsg)
            )
          )
      }
  }

  def updateContact(c: Contact): Future[Either[ServiceResponse, Contact]] = {
    logger.debug(s"updateContact(), contact = $c")
    contactsRepo
      .read(c.email)
      .flatMap {
        case None =>
          Future.successful(
            Left(
              ServiceResponse(status = "error", description = s"Contact not found")
            )
          )
        case Some(_) =>
          contactsRepo
            .update(c)
            .map(Right(_))
            .recoverWith {
              case e: Throwable =>
                val errorMsg = s"Error when updating contact, ${e.getMessage}"
                logger.error(errorMsg, e)
                Future.successful(
                  Left(
                    ServiceResponse(status = "Error", description = errorMsg)
                  )
                )
            }
      }
  }

  def deleteContact(email: String): Future[Either[ServiceResponse, Unit]] = {
    logger.debug(s"deleteContact(), email = $email")
    contactsRepo
      .delete(email)
      .map {
        case Some(_) => Right(())
        case None =>
          Left(
            ServiceResponse(status = "error", description = s"Contact not found")
          )
      }
      .recoverWith {
        case e: Throwable =>
          val errorMsg = s"Error when deleting contact, ${e.getMessage}"
          logger.error(errorMsg, e)
          Future.successful(
            Left(
              ServiceResponse(status = "Error", description = errorMsg)
            )
          )
      }
  }

}
