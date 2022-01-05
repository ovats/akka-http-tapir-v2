package com.demo.endpoints

import sttp.tapir.{endpoint, stringBody, Endpoint}

class HelloWorldEndpoint {

  val helloWorldEndpoint: Endpoint[Unit, String, String, Any] =
    endpoint.get
      .tag("Hello World!")
      .in("hello")
      .out(stringBody)
      .errorOut(stringBody)

}
