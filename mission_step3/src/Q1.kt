/*
fun main(args : Array<String>){

    val generator = Generator()

    println("---영상클립---")
    generator.generate(13)
}


class VideoData(id: String, playTime: Int, idNum: Int ){
    var id = id
    var playTime = playTime
    var idNum = idNum
    var nextDataId = ""

    fun printData(){
        println("제목${idNum}(${id}):${playTime}")
    }
}

class Generator(){
    var idNum = 1
    var idList = mutableListOf<String>()
    var videoDataList = mutableListOf<VideoData>()


    fun generate(dataCount: Int){
        for(count in 1..dataCount){
            while(true){
                var ranId = generateRandomId()
                if(checkUniqueId(ranId)){
                    var vData = VideoData(ranId, (1..15).random(), idNum++)
                    vData.printData()
                    videoDataList.add(vData)
                    break
                }
            }
        }
    }

    fun generateRandomId(): String{
        var ranId = ""
        for(i in 1..4)
            ranId += 'a' + (0..5).random()   // a~f만 사용

        return ranId
    }

    fun checkUniqueId(id: String): Boolean{ // 이진탐색으로 나중에 구현해보기?
        idList.forEach {
            if(id.equals(it)){
                println("id 중복 발생, 시도id : ${id}, ${idList.indexOf(it) + 1}번째와 중복")
                return false
            }
        }

        idList.add(id)
        return true
    }

}*/
