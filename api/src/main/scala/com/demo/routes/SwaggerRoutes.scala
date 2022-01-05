package com.demo.routes

import akka.http.scaladsl.server.Route
import sttp.tapir.Endpoint
import sttp.tapir.docs.openapi.OpenAPIDocsInterpreter
import sttp.tapir.openapi.circe.yaml.RichOpenAPI
import sttp.tapir.swagger.akkahttp.SwaggerAkka

class SwaggerRoutes(endpoints: Iterable[Endpoint[_, _, _, _]], title: String, version: String) {

  val routes: Route = new SwaggerAkka(
    OpenAPIDocsInterpreter()
      .toOpenAPI(
        endpoints,
        title = title,
        version = version,
      )
      .toYaml
  ).routes

}
