package com.demo

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import com.demo.data.SampleData.addSampleData
import com.demo.endpoints.{ContactsEndpoints, HelloWorldEndpoint, PingEndpoints}
import com.demo.routes.{ContactRoutes, SwaggerRoutes}
import com.typesafe.scalalogging.LazyLogging
import repository.InMemoryContactsRepository
import services.{ContactsService, PingService}

import scala.util.{Failure, Success}

object MainApi extends LazyLogging {

  def main(args: Array[String]): Unit = {
    logger.info(s"Starting ...")

    implicit val system: ActorSystem = ActorSystem("akka-http-tapir-v2")
    import system.dispatcher

    // These values could be retrieved from conf file
    val interface: String      = "0.0.0.0"
    val port: Int              = 8080
    val serviceName: String    = "akka-http-tapir-v2"
    val serviceVersion: String = "0.1"

    // Repository
    val contactRepo = new InMemoryContactsRepository()
    addSampleData(contactRepo)

    // Services
    val contactsService = new ContactsService(contactRepo)
    val pingService     = new PingService()

    // Endpoints
    val helloWorldEndpoint = new HelloWorldEndpoint()
    val pingEndpoints      = new PingEndpoints()
    val contactEndpoints   = new ContactsEndpoints()
    val openAPIDocRoute = new SwaggerRoutes(
      contactEndpoints.allEndpoints ++ pingEndpoints.allEndpoints ++ List(
            helloWorldEndpoint.helloWorldEndpoint
          ),
      serviceName,
      serviceVersion,
    )

    // Routes
    val routes = new ContactRoutes(contactsService, contactEndpoints).routes ~ openAPIDocRoute.routes

    // Start the server
    Http()
      .newServerAt(interface, port)
      .bind(routes)
      .onComplete {
        case Success(_) => logger.info(s"Started!")
        case Failure(e) => logger.info("Failed to start ... ", e)
      }

  }
}
