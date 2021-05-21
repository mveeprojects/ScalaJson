package spray

case class Colour(name: String, red: Int, green: Int, blue: Int)

case class ColourOptName(name: Option[String] = None, red: Int, green: Int, blue: Int)

case class Colours(colours: List[Colour])

case class ColoursWithOptNames(colourOptNames: List[ColourOptName])
