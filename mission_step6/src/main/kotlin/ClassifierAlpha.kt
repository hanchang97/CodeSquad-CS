import kotlin.math.*

class ClassifierAlpha {

    companion object {
        fun sum(factors: Set<Int>) = factors.fold(0){ total, n -> total + n}
    }

    fun factors(number: Int) : Set<Int> =
        HashSet<Int>().apply{
            for (pod in 1..sqrt(number.toDouble()).toInt()) {
                if (number % pod == 0) {
                    add(pod)
                    add(number / pod)
                }
            }
        }

    fun isPrime(number: Int) : Boolean =
        number > 1 && (factors(number) == mutableSetOf(1, number))

    fun isPerfect(number: Int) : Boolean { // 완전수 = 자기 자신을 제외한 양의 약수를 더했을 때 자기 자신이 되는 양수의 정수
        return ClassifierAlpha.sum(factors(number)) - number == number
    }

    fun isAbundant(number: Int) : Boolean {
        return ClassifierAlpha.sum(factors(number)) - number > number
    }

    fun isDeficient(number: Int) : Boolean {
        return ClassifierAlpha.sum(factors(number)) - number < number
    }

    fun isSquared(number: Int) : Boolean{
        (1..number).map {
            if(it * it == number)
                return true
        }
        return false
    }

    fun getTotalResult(number: Int) : MutableList<String> =
        mutableListOf<String>().apply {
            if(isPerfect(number)) add("perfect")
            if(isAbundant(number)) add("abundant")
            if(isDeficient(number)) add("deficient")
            if(isPrime(number)) add("prime")
            if(isSquared(number)) add("squared")
        }
}