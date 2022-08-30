package play

import play.api.libs.json._

// example below is from the Play Json GitHub repo - https://github.com/playframework/play-json

case class Resident(name: String, age: Int, role: Option[String])

object PlayMain extends App {

  val json: JsValue = Json.parse(
    """
        {
          "name" : "Watership Down",
          "location" : {
            "lat" : 51.235685,
            "long" : -1.309197
          },
          "residents" : [ {
            "name" : "Fiver",
            "age" : 4,
            "role" : null
          }, {
            "name" : "Bigwig",
            "age" : 6,
            "role" : "Owsla"
          } ]
        }
    """)

  def basicReadingAndWriting(): Unit = {

    println(s"-- basicReadingAndWriting --")

    val jsonString = Json.stringify(json)
    println(s"- Json String from JsValue: $jsonString")

    // Simple path lookup (\)
    val lat = (json \ "location" \ "lat").get
    println(s"- location.lat value: $lat")

    val bigwig = (json \ "residents" \ 1).get
    println(s"- Item at position 1 of the residents array: $bigwig")

    // Recursive path lookup (\\)
    val names = json \\ "name"
    println(s"- Recursive lookup for names: $names")

    // Index lookup
    val name = json("name")
    println(s"- First instance of name within the json structure: $name")

    val bigwig1 = json("residents")(1)
    println(s"- Instance of resident at position 1 of the residents array: $bigwig1")
  }

  def readingAndWritingObjects(): Unit = {
    println("\n-- readingAndWritingObjects --")

    // Validate is preferred as it returns a JsResult (JsSuccess or JsError) which may contain an error if the JSON is malformed.
    // .as is unsafe (similar to .get on Option)
    // .asOpt is safe but will only return a None in the case of an error
    val validateNameSuccess = (json \ "name").validate[String]
    println(s"- Validate where key exists (JsSuccess): $validateNameSuccess")

    val validateKeyDoesNotExist = (json \ "blah").validate[String]
    println(s"- Validate where key does not exist (JsError): $validateKeyDoesNotExist")
    println(s"- Using .getOrElse on JsError: ${validateKeyDoesNotExist.getOrElse("some error message")}")
  }

  def automaticConversion(): Unit = {
    println("\n-- automaticConversion --")

    // Could use Reads and Writes, or could just use Format as this encapsulates both.
    // With the Reads and/or Writes in scope, I can then easily convert my class using toJson and fromJson.
    // implicit val residentReads = Json.reads[Resident]
    // implicit val residentWrites = Json.writes[Resident]

    implicit val residentFormat: OFormat[Resident] = Json.format[Resident]

    val residentAtFirstElement: Either[JsError, Resident] = json("residents")(1).validate[Resident] match {
      case s: JsSuccess[Resident] => Right(s.get)
      case e: JsError => Left(e)
    }

    val result = residentAtFirstElement match {
      case Right(resident) => s"Name: ${resident.name}, Age: ${resident.age}  Role: ${resident.role}"
      case Left(errorString) => s"Something went wrong: $errorString"
    }

    println(s"- Creating a case class instance based on json and retrieving the values: $result")

  }

  basicReadingAndWriting()
  readingAndWritingObjects()
  automaticConversion()
}
