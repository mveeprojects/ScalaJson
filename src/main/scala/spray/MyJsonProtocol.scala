package spray

import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait MyJsonProtocol extends DefaultJsonProtocol {
  implicit val coloursFormat: RootJsonFormat[Colours]                              = jsonFormat1(Colours)
  implicit val coloursWithOptNamePalletFormat: RootJsonFormat[ColoursWithOptNames] = jsonFormat1(ColoursWithOptNames)
  implicit val colourFormat: RootJsonFormat[Colour]                                = jsonFormat4(Colour)
  implicit val colourOptNameFormat: RootJsonFormat[ColourOptName]                  = jsonFormat4(ColourOptName)
}
