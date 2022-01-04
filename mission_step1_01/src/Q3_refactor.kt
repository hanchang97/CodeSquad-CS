// 3 - 1바이트 덧셈기

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>){

    val br = BufferedReader(InputStreamReader(System.`in`))

    val gate = Gate()
    val halfAdder = HalfAdder(gate)
    val fullAdder = FullAdder(halfAdder)
    val byteAdder = ByteAdder(fullAdder)

   // 두 boolean 배열 모두 같은 비트 수 입력
    print("byteA 입력 : ")
    var byteA = br.readLine().split(' ').map{ it.toInt() == 1 } as ArrayList<Boolean>
    // 입력은 정수형으로 1 = true, 0 = false

    print("byteB 입력 : ")
    var byteB = br.readLine().split(' ').map{ it.toInt() == 1 } as ArrayList<Boolean>

    // 입력 후 boolean 배열 형태 확인
    println("byteA : ${byteA}")
    println("byteB : ${byteB}")

    println("결과 : ${byteAdder.byteAdd(byteA, byteB)}")


    //halfAdderTest()
    //fullAdderTest()
    //byteAdderTest()
}


class Gate(){
    fun AND(bitA: Boolean, bitB: Boolean): Boolean{
        return bitA && bitB
    }

    fun OR(bitA: Boolean, bitB: Boolean): Boolean{
        return bitA || bitB
    }

    fun NAND(bitA: Boolean, bitB: Boolean): Boolean{
        return !(AND(bitA, bitB))
    }

    fun XOR(bitA: Boolean, bitB: Boolean): Boolean{
        return !(bitA == bitB)
    }
}

class HalfAdder(gate: Gate){
    var gate = gate

    fun getSum(bitA: Boolean, bitB: Boolean) = gate.XOR(bitA, bitB)

    fun getCarry(bitA: Boolean, bitB: Boolean) = gate.AND(bitA, bitB)

    fun halfAdd(bitA: Boolean, bitB: Boolean) = mutableListOf(getCarry(bitA, bitB), getSum(bitA, bitB))
    // [ 캐리, 합 ] 형태로 반환
}


class FullAdder(halfAdder: HalfAdder){

    var halfAdder = halfAdder

    fun fullAdd(bitA:Boolean, bitB:Boolean, carryIn:Boolean) : MutableList<Boolean>{
        var bitResult = halfAdder.halfAdd(bitA, bitB) // 두 개의 입력 비트 연산 결과
        var carryInResult = halfAdder.halfAdd(carryIn, bitResult[1]) // 입력으로 받은 캐리와 위 연산 결과로 나온 sum을 계산해준다

        var resultSum = carryInResult[1]
        var resultCarry = carryInResult[0] || bitResult[0]

        return mutableListOf(resultCarry, resultSum)
    }
}

class ByteAdder(fullAdder: FullAdder){
    var fullAdder = fullAdder
    var size = 0

    fun byteAdd(byteA: ArrayList<Boolean>, byteB: ArrayList<Boolean>) : MutableList<Boolean>{
        // 비트수가 적은 배열의 빈 공간을 false로 채운다
        var byteAsize = byteA.size
        var byteBsize = byteB.size

        if(byteAsize >= byteBsize){
            size = byteAsize
            for(index in byteBsize..size-1){
                byteB.add(false)
            }
        }
        else {
            size = byteBsize
            for(index in byteAsize..size-1){
                byteA.add(false)
            }
        }


        var answerList = mutableListOf<Boolean>()

        var previousCarryOut = false

        for(i in 0..size-1){
            answerList.add(fullAdder.fullAdd(byteA[i], byteB[i], previousCarryOut)[1]) // sum 저장
            previousCarryOut = fullAdder.fullAdd(byteA[i], byteB[i], previousCarryOut)[0] // 캐리 저장
        }

        if(previousCarryOut)
            answerList.add(true)
        else
            answerList.add(false)

        return answerList
    }
}

// Test function

fun halfAdderTest(){
    var gateTest1 = Gate()
    var halfAdderTest1 = HalfAdder(gateTest1)

    println("----- halfAdderTest start -----")
    println(halfAdderTest1.halfAdd(true, true))
    println(halfAdderTest1.halfAdd(true, false))
    println(halfAdderTest1.halfAdd(false, true))
    println(halfAdderTest1.halfAdd(false, false))
    println("----- halfAdderTest end -----")
}


fun fullAdderTest(){
    var gateTest = Gate()
    var halfAdderTest = HalfAdder(gateTest)
    var fullAdderTest = FullAdder(halfAdderTest)

    println("----- fullAdderTest start -----")
    println(fullAdderTest.fullAdd(true, true, true))
    println(fullAdderTest.fullAdd(true, true, false))

    println(fullAdderTest.fullAdd(true, false, true))
    println(fullAdderTest.fullAdd(true, false, false))

    println(fullAdderTest.fullAdd(false, true, true))
    println(fullAdderTest.fullAdd(false, true, false))

    println(fullAdderTest.fullAdd(false, false, true))
    println(fullAdderTest.fullAdd(false, false, false))
    println("----- fullAdderTest end -----")
}

fun byteAdderTest(){
    var gateTest = Gate()
    var halfAdderTest = HalfAdder(gateTest)
    var fullAdderTest = FullAdder(halfAdderTest)
    var byteAdderTest = ByteAdder(fullAdderTest)

    println("----- byteAdderTest start -----")
    println(byteAdderTest.byteAdd(arrayListOf(true, true, false, true, true, false, true, false),
        arrayListOf(true, false, true, true, false, false, true, true)))
    println("----- byteAdderTest end -----")
}

