/*
// 2 - 반쪽덧셈과 전체덧셈

// 1. 입력받기
// 2. 합 구하기
// 3. 자리올림 구현

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>){

    val br = BufferedReader(InputStreamReader(System.`in`))

    val gate = Gate()
    val halfAdder = HalfAdder(gate)
    val fullAdder = FullAdder(halfAdder)

    // half adder
    println("half add")
    print("bitA = ")
    var bitAstr = br.readLine()
    var bitA = bitAstr.equals("true")

    print("bitB = ")
    var bitBstr = br.readLine()
    var bitB = bitBstr.equals("true")

    println("결과 : ${halfAdder.halfAdd(bitA, bitB)}")
    println()

    // full adder
    println("full add")
    print("bitA = ")
    var bitAstr2 = br.readLine()
    var bitA2 = bitAstr2.equals("true")

    print("bitB = ")
    var bitBstr2 = br.readLine()
    var bitB2 = bitBstr2.equals("true")

    print("캐리 = ")
    var carryInStr = br.readLine()
    var carryIn = carryInStr.equals("true")

    println("결과 : ${fullAdder.fullAdd(bitA2, bitB2, carryIn)}")


    //halfAdderTest()
    //fullAdderTest()

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

class FullAdder(halfAdder: HalfAdder){

    var halfAdder = halfAdder

    // 전가산기 논리 회로도 흐름에 따라 구현
    fun fullAdd(bitA:Boolean, bitB:Boolean, carryIn:Boolean) : MutableList<Boolean>{
        var bitResult = halfAdder.halfAdd(bitA, bitB) // 두 개의 입력 비트 연산 결과
        var carryInResult = halfAdder.halfAdd(carryIn, bitResult[1]) // 입력으로 받은 캐리와 위 연산 결과로 나온 sum을 계산해준다

        var resultSum = carryInResult[1]
        var resultCarry = carryInResult[0] || bitResult[0]

        return mutableListOf(resultCarry, resultSum)
    }
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

*/
