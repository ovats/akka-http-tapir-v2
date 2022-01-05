package com.demo.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.demo.endpoints.ContactsEndpoints
import services.ContactsService
import sttp.tapir.server.akkahttp.AkkaHttpServerInterpreter

class ContactRoutes(contactsService: ContactsService, contactsEndpoints: ContactsEndpoints) {

  private val getAllContactsRoute: Route =
    AkkaHttpServerInterpreter().toRoute(contactsEndpoints.getAllContactsEndpoint)(contactsService.getAll)

  private val addContactRoute: Route =
    AkkaHttpServerInterpreter().toRoute(contactsEndpoints.addContactEndpoint)((contactsService.addContact _).tupled)

  private val getContactRoute: Route =
    AkkaHttpServerInterpreter().toRoute(contactsEndpoints.getContactEndpoint)(contactsService.getContact)

  private val updateContactRoute: Route =
    AkkaHttpServerInterpreter().toRoute(contactsEndpoints.updateContactEndpoint)(contactsService.updateContact)

  private val deleteContactRoute: Route =
    AkkaHttpServerInterpreter().toRoute(contactsEndpoints.deleteContactEndpoint)(contactsService.deleteContact)

  val routes: Route =
    addContactRoute ~ getAllContactsRoute ~ getContactRoute ~ updateContactRoute ~ deleteContactRoute

}
