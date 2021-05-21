package spray

import spray.json._

object SprayMain extends App with MyJsonProtocol {

  // This works fine
  println("Colour example:")
  val colourJson = Colour("CadetBlue", 95, 158, 160).toJson
  val colourSolo = colourJson.convertTo[Colour]
  println(colourSolo.name)

  // This works fine
  println("\nColour with optional field example:")
  val colourJsonSome = ColourOptName(Some("CadetBlue"), 95, 158, 160).toJson
  val colourJsonNone = ColourOptName(None, 95, 158, 160).toJson
  val colourSome     = colourJsonSome.convertTo[ColourOptName]
  val colourNone     = colourJsonNone.convertTo[ColourOptName]
  println(colourSome.name)
  println(colourNone.name)

//  // Not yet working (NPE)
//  println("\nColours example:")
//  val colour        = List(Colour("CadetBlue", 95, 158, 160))
//  val coloursJson   = Colours(colour).toJson
//  val coloursPallet = coloursJson.convertTo[Colours]
//  println(coloursPallet)

//  // Not yet working (NPE)
//  println("\nColours with optional field in nested object example:")
//  val colourJsonSome1 = ColourOptName(Some("CadetBlue"), 95, 158, 160).toJson
//  val colourSome1        = colourJsonSome1.convertTo[ColourOptName]
//  val coloursOptNameJson = ColoursWithOptNames(List(colourSome1)).toJson
//  val coloursOptNames    = coloursOptNameJson.convertTo[ColoursWithOptNames]
//  println(coloursOptNames)
}
