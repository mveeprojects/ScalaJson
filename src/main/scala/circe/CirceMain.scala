package circe

import io.circe._
import io.circe.generic.auto._
import io.circe.parser._
import utils.FileReader.readInputFile

case class Hit(slug: String)
case class Result(hits: List[Hit])
case class Results(results: List[Result])

object CirceMain extends App {

  val jsonString: String = readInputFile()

  deserialiseJson(jsonString) match {
    case Right(result)    => result.results.map(r => r.hits.foreach(println))
    case Left(circeError) => println(s"Crikey, something went wrong\n$circeError")
  }

  def deserialiseJson(jsonString: String): Either[Error, Results] = decode[Results](jsonString)
}
