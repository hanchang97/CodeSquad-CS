// 2 - 반쪽덧셈과 전체덧셈

// 1. 입력받기
// 2. 합 구하기
// 3. 자리올림 구현

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))

    print("bitA = ")
    val bitAstr = br.readLine()
    val bitA = bitAstr.equals("true")

    print("bitB = ")
    val bitBstr = br.readLine()
    val bitB = bitBstr.equals("true")

    val gate = Gate()
    val halfAdder = HalfAdder(gate)

    val answerList = halfAdder.halfAdd(bitA, bitB)
    println(answerList)

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

    fun halfAdd(bitA: Boolean, bitB: Boolean): MutableList<Boolean>{

        var sum = false
        var carry = false

        if(this.gate.AND(bitA, bitB)){
            carry = true
            sum = false
        }
        else{
            carry = false
            sum = gate.OR(bitA, bitB)
        }

        return mutableListOf(carry, sum)
    }
}

