fun main(args: Array<String>){
 //각 언어로 만들어진 다음 2개 클래스에서 중복된 코드를 줄이고, 함수형 표현으로 최대한 개선한다.

    val classifier = ClassifierAlpha()
    (1..100).reduce { acc , i -> printResult(i) } //  reduce 특성상 2부터 검사를 하기 위해 1..100 으로 설정

    //println(classifier.isPrime(23))
}

fun printResult(num:Int): Int{
    val classfier = ClassifierAlpha()
    print("${num} : ")
    classfier.getTotalResult(num).forEach { print("${it}, ") }
    println()
    return num
}