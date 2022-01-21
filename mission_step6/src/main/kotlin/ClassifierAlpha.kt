import kotlin.math.*

class ClassifierAlpha {

    companion object {
        fun sum(factors: Set<Int>) = factors.fold(0){ total, n -> total + n}
    }

    val isPrime = {num : Int -> num > 1 && factors(num) == mutableSetOf(1, num)}
    val factors = {num: Int ->
        HashSet<Int>().apply {
            for (pod in 1..sqrt(num.toDouble()).toInt()) {
                if (num % pod == 0) {
                    add(pod)
                    add(num / pod)
                }
            }
        }
    }

    // 완전수 = 자기 자신을 제외한 양의 약수를 더했을 때 자기 자신이 되는 양수의 정수
    val isPerfect = {num: Int -> sum(factors(num)) - num == num }

    val isAbundant = {num: Int -> sum(factors(num)) - num > num }

    val isDeficient = {num: Int -> sum(factors(num)) - num < num }

    val isSquared = {num: Int -> (1..num).any{ it * it == num}}


    fun getTotalResult(number: Int) : MutableList<String> =
        mutableListOf<String>().apply {
            if(isPerfect(number)) add("perfect")
            if(isAbundant(number)) add("abundant")
            if(isDeficient(number)) add("deficient")
            if(isPrime(number)) add("prime")
            if(isSquared(number)) add("squared")
        }
}