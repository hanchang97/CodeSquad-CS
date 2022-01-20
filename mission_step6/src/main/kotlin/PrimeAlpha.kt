import kotlin.math.*

class PrimeAlpha {
    fun isPrime(number: Int) : Boolean =
        number > 1 && (factors(number) == mutableSetOf(1, number))

    fun factors(number: Int) : Set<Int> =
        HashSet<Int>().apply{
            for (pod in 1..sqrt(number.toDouble()).toInt()) {
                if (number % pod == 0) {
                    add(pod)
                    add(number / pod)
                }
            }
        }
    // apply는 항상 수신 객체를 반환


    /*   with을 사용하는 경우
    fun factors(number: Int) : Set<Int> =
    with(HashSet<Int>()){
        for (pod in 1..sqrt(number.toDouble()).toInt()) {
            if (number % pod == 0) {
                add(pod)
                add(number / pod)
            }
        }
        this  // 마지막에 람다식에서 값 반환
    }*/
}
