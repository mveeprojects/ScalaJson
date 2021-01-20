import FileReader.readInputFile
import io.circe._
import io.circe.generic.auto._
import io.circe.parser._

import scala.io.{BufferedSource, Source}

case class Hit(slug: String)
case class Result(hits: List[Hit])
case class Results(results: List[Result])

object Main extends App {

  val jsonString: String = readInputFile()

  deserialiseJson(jsonString) match {
    case Right(result)    => result.results.map(r => r.hits.foreach(println))
    case Left(circeError) => println(s"Crikey, something went wrong\n$circeError")
  }

  def deserialiseJson(jsonString: String): Either[Error, Results] = decode[Results](jsonString)
}

object FileReader {

  val inputFile: String = "input.json"

  def readInputFile(inputFilePath: String = inputFile): String = {
    val source: BufferedSource = Source.fromResource(inputFilePath)
    val jsonString             = source.getLines.mkString
    source.close
    jsonString
  }
}
