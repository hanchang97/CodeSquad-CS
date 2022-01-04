- ### 이진 덧셈기
  + ### 미션1 : 디지털 논리 게이트 함수 - 구현 완료
  <br>
  
  + ### 미션2 : 반쪽덧셈과 전체덧셈 - 구현 완료
    + 미션2 결과 예시

    ![image](https://user-images.githubusercontent.com/69443895/147958692-b672f46d-e1a0-4d1e-943f-8a4567ba077b.png)

```kotlin
class HalfAdder(gate: Gate){
    var gate = gate

    fun getSum(bitA: Boolean, bitB: Boolean) = gate.XOR(bitA, bitB)

    fun getCarry(bitA: Boolean, bitB: Boolean) = gate.AND(bitA, bitB)

    fun halfAdd(bitA: Boolean, bitB: Boolean) = mutableListOf(getCarry(bitA, bitB), getSum(bitA, bitB))
    // [ 캐리, 합 ] 형태로 반환
}
```
- 반가산기는 진리표에 따라 간단하게 carry는 AND, sum은 XOR 연산을 통해 구현할 수 있다
<br>

![image](https://user-images.githubusercontent.com/69443895/148030969-079397d6-1463-4b45-943d-30f15c738997.png)

```kotlin
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
```
  - 전가산기의 논리회로도는 위의 사진과 같이 나타낼 수 있다
  - 처음 A, B 입력을 반가산기를 통해 계산한 결과가 bitResult에 저장된다
  - bitResult의 sum과 자리올림수 입력(carryIn)을 반가산기를 통해 계산한 결과가 carryInResult에 저장된다
  - carryInResult의 sum이 전체 연산의 합 비트가 되고, carryInReuslt의 carry와 bitResult의 carry를 OR 연산한 것이 전체 연산의 자리올림 비트가 된다 
<br>

   + ### 미션3 : 1바이트 덧셈기 - 구현 완료
      + 미션3 결과 예시
    
   ![image](https://user-images.githubusercontent.com/69443895/147958431-f2d9577f-54de-4a01-8857-1b4e52ffaa41.png)
    
    
   ```kotlin
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
   ```
   - 두 배열의 크기가 다른 경우를 위해 더 큰 크기를 구하고 작은 배열의 빈공간을 계산 후 false로 채워넣도록 구현했다
   - 이전 자리에서 발생한 캐리를 가져와서 현재 자리를 계산하도록 구현했다
   - 마지막 자리에서 캐리가 발생하는 경우도 처리했다
