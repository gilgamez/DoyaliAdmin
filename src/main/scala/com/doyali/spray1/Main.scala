package com.doyali.spray1

import spray.routing.SimpleRoutingApp

object Main extends App with SimpleRoutingApp {
  startServer(interface = "localhost", port = 8080) {
    path("hello") {
      get {
        complete {
          <h1>Say hello to spray</h1>
        }
      }
    } ~
      path("photo") {
        path("all") {} ~
          pathPrefix("order" / IntNumber) {
            orderId =>
              path("") {}
          }
      }
  }
}