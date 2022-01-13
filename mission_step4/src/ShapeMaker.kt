import org.w3c.dom.css.Rect
import kotlin.math.round

class ShapeMaker {
    fun makeShape(locationList : MutableList<Pair<Double, Double>>) : Shape?{
        var size = locationList.size

        when(size){
            2 -> {
                // 직선
               return Straight(locationList[0].first, locationList[0].second,
                               locationList[1].first, locationList[1].second)
            }
            3 -> {
                // 삼각형
                return Triangle(locationList[0].first, locationList[0].second,
                                locationList[1].first, locationList[1].second,
                                locationList[2].first, locationList[2].second)
            }
            4 -> {
                // 사각형
                return Rectangle(locationList)
            }
            else -> {

                return null
            }
        }
    }
}