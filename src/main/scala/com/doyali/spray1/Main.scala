package com.doyali.spray1

import spray.routing.SimpleRoutingApp
import org.json4s.native.JsonMethods._
import org.json4s.JsonDSL.WithBigDecimal._
import com.drew.imaging.ImageMetadataReader
import java.io.BufferedInputStream
import scala.collection.JavaConversions._
import org.json4s._
import scala.Some
import com.drew.metadata.Tag


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
          pretty(render(List("IMG_4847.JPG", "foto 1-1.JPG")))
        }

      } ~
      path("id" / PathElement) {
        id =>
          complete {
            implicit def tag2jvalue(x: Tag) = JObject(x.getTagName().toString,JString(x.getDescription()))

            val result = Some(id)
            import scalax.io.Resource._
            val reader = fromClasspath(id).map { is =>  ImageMetadataReader.readMetadata(new BufferedInputStream(is), true) }
            render( reader.now.getDirectories().flatMap( d => d.getTags()) )
            //               pretty(render(result))
          }
      }
    }
  }
}
