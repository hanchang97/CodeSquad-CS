- ### 진법 변환기
  + #### 미션1 : dec2bin() 함수 - 구현 완료
  + #### 미션2 : bin2dec() 함수 - 구현 완료
  + #### 추가 : 10진수 -> 2진수 -> 16진수 - 구현 완료
    + 결과 예시
    
    ![image](https://user-images.githubusercontent.com/69443895/148054185-2119c115-f549-4b51-98d9-4267a34543f5.png)
    
    
    ```kotlin
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
 
    ```
    
      + 10진수 형태의 수를 dec2bin함수를 통해 2진수 형태의 배열로 변환한다
      + 16진수로 변환을 편리하게 하기 위해 배열의 크기가 4로 나누어 떨어지도록 0을 추가해준다
      + 4개씩 끊어서 10진수 형태로 만들어준 다음 이를 16진수 값에 해당하는 문자로 변경한다(dec2hexadecString )
      + 위 문자를 16진수 결과 배열에 차례대로 저장한다
