import kotlin.math.*

class Straight(var x1:Double, var y1: Double, var x2: Double, var y2: Double) : Shape {
// 클래스에서는 계산 후 값만 리턴해주기!! -> 출력은 이를 동작시키는 곳에서 하기 ex> main함수

    private var length = 0.0

    fun getLength() = length

    override fun calculate() {
        length = sqrt(((x2-x1) as Double).pow(2) + ((y2-y1) as Double).pow(2))
    }

    // 소수점 여섯째 자리 까지 반올림
    override fun printShapeValue() {
        println("두 점 사이 거리 = ${round(length * 1000000) / 1000000}")
    }

    fun getSquaredState() = (x2-x1).pow(2) + (y2-y1).pow(2)
}