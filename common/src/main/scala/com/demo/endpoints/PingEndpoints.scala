package com.demo.endpoints

import sttp.tapir.{endpoint, header, plainBody, stringBody, Endpoint}

class PingEndpoints {

  val pingEndpoint: Endpoint[Unit, String, String, Any] =
    endpoint.get
      .tag("Ping")
      .summary("Use this endpoint to ping the app")
      .description("Ping the app")
      .in("ping")
      .out(
        plainBody[String]
          .example("pong")
      )
      .errorOut(stringBody)

  val allEndpoints: Iterable[Endpoint[_, _, _, _]] = List(pingEndpoint)
}
