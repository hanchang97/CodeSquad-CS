import kotlin.math.round
import kotlin.math.sqrt

class Triangle(var x1:Double, var y1: Double,
               var x2: Double, var y2: Double,
               var x3:Double, var y3: Double): Shape {

    private var area = 0.0
    fun getArea() = area

    var triangle = mutableListOf<Straight>(Straight(x1, y1, x2, y2), Straight(x1, y1, x3, y3), Straight(x2, y2, x3, y3))

    override fun calculate() {
        var sideList = mutableListOf<Double>()

        triangle.forEach {
            it.calculate()
            sideList.add(it.getLength())
        }

        var s = (sideList[0] + sideList[1] + sideList[2]) / 2
        area = sqrt(s * (s-sideList[0]) * (s - sideList[1]) * (s - sideList[2]))
    }

    override fun printShapeValue() {
        println("삼각형 넓이 = ${round(area * 10) / 10}")
    }
}