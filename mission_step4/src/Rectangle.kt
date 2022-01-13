import kotlin.math.round

class Rectangle(locationList : MutableList<Pair<Double, Double>>): Shape {

    private var area = 0.0
    private var isRectangle = true
    private var sortedLocationList = locationList.sortedWith(compareBy({it.first}, {it.second}))

    override fun calculate() {
        isRectangle = checkRectangle()
        if(isRectangle){
            var width = Straight(sortedLocationList[0].first, sortedLocationList[0].second,
                sortedLocationList[2].first, sortedLocationList[2].second)
            var height = Straight(sortedLocationList[2].first, sortedLocationList[2].second,
                sortedLocationList[3].first, sortedLocationList[3].second)

            width.calculate()
            height.calculate()

            area = width.getLength() * height.getLength()
        }
    }

    override fun printShapeValue() {
        if(isRectangle)
            println("사각형 넓이 = ${round(area * 10) / 10}")
        else
            println("직사각형이 아닙니다.")
    }

    fun checkRectangle(): Boolean{
        var cross = Straight(sortedLocationList[0].first, sortedLocationList[0].second,
                             sortedLocationList[3].first, sortedLocationList[3].second).getSquaredState()
        //println(cross)

        var side1_1 = Straight(sortedLocationList[0].first, sortedLocationList[0].second,
                               sortedLocationList[1].first, sortedLocationList[1].second).getSquaredState()
        var side1_2 = Straight(sortedLocationList[1].first, sortedLocationList[1].second,
                               sortedLocationList[3].first, sortedLocationList[3].second).getSquaredState()

        //println("side1 ${side1_1}  ${side1_2}")

        var side2_1 = Straight(sortedLocationList[0].first, sortedLocationList[0].second,
                               sortedLocationList[2].first, sortedLocationList[2].second).getSquaredState()
        var side2_2 = Straight(sortedLocationList[2].first, sortedLocationList[2].second,
                               sortedLocationList[3].first, sortedLocationList[3].second).getSquaredState()

        //println("side1 ${side2_1}  ${side2_2}")

        return (side1_1 + side1_2 == cross) && (side2_1 + side2_2 == cross)
    }
}