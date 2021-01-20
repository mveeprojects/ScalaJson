package utils

import scala.io.{BufferedSource, Source}

object FileReader {

  val inputFile: String = "input.json"

  def readInputFile(inputFilePath: String = inputFile): String = {
    val source: BufferedSource = Source.fromResource(inputFilePath)
    val jsonString             = source.getLines.mkString
    source.close
    jsonString
  }
}
