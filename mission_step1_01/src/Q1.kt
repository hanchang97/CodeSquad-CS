// 1 - 디지털 논리 게이트 함수 구현
fun main(args: Array<String>){
    var gate = Gate()

    println(gate.AND(true, true))
    println(gate.OR(true, false))
    println(gate.NAND(true, true))
    println(gate.XOR(true, false))

}

class Gate(){

    fun AND(first: Boolean, second: Boolean): Boolean{
        return first && second
    }

    fun OR(first: Boolean, second: Boolean): Boolean{
        return first || second
    }

    fun NAND(first: Boolean, second: Boolean): Boolean{
        return !(AND(first, second))
    }

    fun XOR(first: Boolean, second: Boolean): Boolean{
        return !(first == second)
    }
}