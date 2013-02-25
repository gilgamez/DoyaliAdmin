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
    pathPrefix("photo") {
      path("all") {
        complete {
          <ul>
            <li>IMG_31001.jpg</li>
          </ul>
        }

      } ~
      path("id" / PathElement) { id =>
          complete {
            "{" + id + "}"
          }
        }
      }
  }
}