// 3 - 1바이트 덧셈기

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))

    val gate = Gate()
    val halfAdder = HalfAdder(gate)
    val fullAdder = FullAdder(halfAdder)

    // 두 배열 모두 8비트 기준
    print("byteA 입력 : ")
    var byteA = br.readLine().split(' ').map{ it.toInt() == 1 }  // 입력은 정수형으로 1 = true, 0 = false

    print("byteB 입력 : ")
    var byteB = br.readLine().split(' ').map{ it.toInt() == 1 }

    // 입력 후 boolean 배열 형태 확인
    println("byteA : ${byteA}")
    println("byteB : ${byteB}")

    var answerList = mutableListOf<Boolean>()

    for(index in 0..byteA.size-1){
        fullAdder.fullAdd(byteA[index], byteB[index], fullAdder.halfAdder.previousCarry)
        answerList.add(fullAdder.halfAdder.sum)

        fullAdder.halfAdder.saveCarry()
        fullAdder.halfAdder.clear()
    }

    if(fullAdder.halfAdder.previousCarry)
        answerList.add(fullAdder.halfAdder.previousCarry)

    print("결과 : ")
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
    var sum = false
    var carry = false // 현재 비트 자리에서 더하기 연산 시 발생하는 캐리
    var previousCarry = false // 이전 자리에서 발생한 캐리

    fun halfAdd(bitA: Boolean, bitB: Boolean){

        if(this.gate.AND(bitA, bitB)){ // (true + true) 인 경우 캐리 발생
            carry = true
            sum = false
        }
        else{
            sum = gate.OR(bitA, bitB)
        }

    }

    fun clear(){  // 상태 초기화
        sum = false
        carry = false
    }

    fun saveCarry(){ // 다음 비트 판단전에 현재 비트에서 발생한 캐리 저장
        previousCarry = carry
    }

}

class FullAdder(halfAdder: HalfAdder){

    var halfAdder = halfAdder

    fun fullAdd(bitA:Boolean, bitB:Boolean, carry:Boolean){
        // 비트 연산
        halfAdder.halfAdd(bitA, bitB)

        // 이전 자리에서 발생한 캐리 더해주기
        halfAdder.halfAdd(halfAdder.sum, carry)
    }
}

