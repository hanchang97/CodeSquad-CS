// 2 - 반쪽덧셈과 전체덧셈

// 1. 입력받기
// 2. 합 구하기
// 3. 자리올림 구현

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))

    // half adder
    print("bitA = ")
    var bitAstr = br.readLine()
    var bitA = bitAstr.equals("true")

    print("bitB = ")
    var bitBstr = br.readLine()
    var bitB = bitBstr.equals("true")

    val gate = Gate()
    val halfAdder = HalfAdder(gate)
    val fullAdder = FullAdder(halfAdder)

    halfAdder.halfAdd(bitA, bitB)
    val answerList1 = mutableListOf(halfAdder.carry, halfAdder.sum)

    print("결과 = ")
    println(answerList1)
    println()

    // full adder
    fullAdder.halfAdder.clear()

    print("bitA = ")
    bitAstr = br.readLine()
    bitA = bitAstr.equals("true")

    print("bitB = ")
    bitBstr = br.readLine()
    bitB = bitBstr.equals("true")

    print("carry = ")
    var carryStr = br.readLine()
    var carry = carryStr.equals("true")

    fullAdder.fullAdd(bitA, bitB, carry)

    val answerList2 = mutableListOf(halfAdder.carry, halfAdder.sum)

    print("결과 = ")
    println(answerList2)

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
    var sum = false
    var carry = false

    fun halfAdd(bitA: Boolean, bitB: Boolean){

        if(this.gate.AND(bitA, bitB)){ // true, true 인 경우 캐리 발생
            carry = true
            sum = false
        }
        else{
            /*
            if(carry == false)  // 기존 비트 A,B의 연산결과 캐리가 존재하는 경우를 제외외
               carry = false
               */

            sum = gate.OR(bitA, bitB)
        }

    }

    fun clear(){  // 상태 초기화
        sum = false
        carry = false
    }
}

class FullAdder(halfAdder: HalfAdder){

    var halfAdder = halfAdder

    fun fullAdd(bitA:Boolean, bitB:Boolean, carry:Boolean){
        // 비트 연산
        halfAdder.halfAdd(bitA, bitB)

        // 입력으로 받은 캐리 추가로 더해주기
        halfAdder.halfAdd(halfAdder.sum, carry)
    }

}

