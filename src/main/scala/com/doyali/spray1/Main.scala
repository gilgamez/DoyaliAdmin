package com.doyali.spray1

import spray.routing.SimpleRoutingApp
import org.json4s.native.JsonMethods._
import org.json4s.JsonDSL.WithBigDecimal._
import com.drew.imaging.ImageMetadataReader
import java.io.BufferedInputStream
import scala.collection.JavaConversions._
import org.json4s._
import com.drew.metadata.{Directory, Tag}


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
            import org.json4s.JsonAST._

            implicit def tag2jvalue(x: Tag) = JObject(
              JField("name",x.getTagName)::
              JField("description",x.getDescription) ::
              JField("",x.)
              Nil)
            //            implicit def tag2jvalue(x: Tag) = JObject(List({"name" -> x.getTagName();"description"->x.getDescription()))
            implicit def directory2json(in: Directory) = JArray(in.getTags.map( tag2jvalue(_)))

            import scalax.io.Resource._
            val reader = fromClasspath(id).map { is =>  ImageMetadataReader.readMetadata(new BufferedInputStream(is), true) }

            val result  = reader.map( _.getDirectories.flatMap(d => d.getTags).map(tag2jvalue(_)))
            pretty(render(result.opt))
            //               pretty(render(result))
          }
      }
    }
  }
}
