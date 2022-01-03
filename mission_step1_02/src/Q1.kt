import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.pow

fun main(args : Array<String>){

    val br = BufferedReader(InputStreamReader(System.`in`))

    // dec2bin
    println("dec2bin")
    print("입력 = ")
    var a = br.readLine().toInt()

    print("결과 = ")
    println(dec2bin((a)))
    println()

    // bin2dec
    println("bin2dec")
    print("입력 = ")
    var binList = br.readLine().split(' ').map{it.toInt()}

    print("결과 = ")
    println(bin2dec(binList))
}


fun dec2bin(num: Int): MutableList<Int>{
    var binList = mutableListOf<Int>()
    var num = num

    while(true){
        var remainder = num % 2

        if(remainder == 1)
            binList.add(1)
        else
            binList.add(0)

        num = num/2
        if(num <= 0)
            break
    }

    return binList
}

fun bin2dec(binList : List<Int>): Int{
    var num = 0
    var powValue = 0

    binList.forEach {
        num += it * (2.0).pow(powValue).toInt()
        powValue++
    }

    return num
}