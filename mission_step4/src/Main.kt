import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.round

fun main(args: Array<String>) {
    val locationInput = LocationInput()
    val locationList = locationInput.enterLocation()
    //println(locationList)

    val shapeMaker = ShapeMaker()
    val shape = shapeMaker.makeShape(locationList)

    shape?.calculate()
    shape?.printShapeValue()

}