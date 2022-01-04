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
    print("1의 자리부터 입력(공백 포함하기) = ")
    var binList = br.readLine().split(' ').map{it.toInt()}

    print("결과 = ")
    println(bin2dec(binList))
    println()


    // 추가
    println("10진수 -> 2진수 -> 16진수")
    print("입력 = ")
    var b = br.readLine().toInt()

    print("결과 = ${dec2bin2hexadec(b)}")
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

fun dec2bin2hexadec(num: Int) : MutableList<String>{

    var binList = dec2bin(num) // 10진수를 2진수로 변환한 배열
    var listSize = binList.size

    var add = when(listSize % 4){
        0 -> 0
        1 -> 3
        2 -> 2
        3 -> 1
        else -> 0
    }

    for(i in 1..add)
        binList.add(0)

    //println(binList)

    var hexadecList = mutableListOf<String>() // 16진수로 변환한 형태를 저장, 이 역시 가장 왼쪽부터 시작

    for(count in 0..binList.size-1){
        if((count + 1)%4 == 0){
            var value =
                (binList[count-3] * 1) + (binList[count-2] * 2) + (binList[count-1] * 4) + (binList[count] * 8)

            hexadecList.add(dec2hexadecString(value))
        }
    }

    return hexadecList
}

fun dec2hexadecString(value: Int): String {
    var str = ""
    if (value <= 9)
        str = value.toString()
    else when (value) {
        10 -> str = "A"
        11 -> str = "B"
        12 -> str = "C"
        13 -> str = "D"
        14 -> str = "E"
        15 -> str = "F"
    }

    return str
}