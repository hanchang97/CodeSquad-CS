import java.io.BufferedReader
import java.io.InputStreamReader

class LocationInput {
    val br = BufferedReader(InputStreamReader(System.`in`))

    fun enterLocation() : MutableList<Pair<Double, Double>>{
        while(true){
            println("> 좌표를 입력하세요. ex> (10,10)-(14,15)-(4,5)...")
            var locationStr = br.readLine().split('-')
            // println(locationStr.size)
            var locationList = mutableListOf<Pair<Double, Double>>()

            for(i in 0..locationStr.size-1){

                var currentLocationStr = locationStr[i]
                var currentSplitByRest = currentLocationStr.split(',')

                var x = currentSplitByRest[0].substring(1).toDouble()
                var y = currentSplitByRest[1].substring(0, currentSplitByRest[1].length-1).toDouble()

                //println("좌표 ${x} ${y}")
                locationList.add(Pair(x,y))
            }

            var isValidInput = true
            // 검사
            for(i in 0..locationList.size-1){
                var x = locationList[i].first
                var y = locationList[i].second

                if(x < 1 || x > 24 || y < 1 || y > 24){
                    isValidInput = false
                    break
                }
            }

            if(isValidInput)
                return locationList
            else
                println("1~24 범위를 벗어나는 좌표가 있습니다. 다시 입력해주세요.")
        }
    }
}