import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args : Array<String>){
    startEdit()
}

fun startEdit(){
    val br = BufferedReader(InputStreamReader(System.`in`))

    var generator = Generator()
    println("---영상클립---")
    generator.generate(13)  // 원하는 데이터 개수 입력 가능

    var videoLinkedList = VideoLinkedList(generator.videoDataList) // 처음 생성한 영상 데이터 리스트 전달

    while(true){
        print("> ")
        var command = StringTokenizer(br.readLine())
        when(command.nextToken()){
            "add" -> {
                var addId = command.nextToken()
                videoLinkedList.addData(addId)
            }
            "insert" -> {
                var insertId = command.nextToken()
                var insertIdx = command.nextToken().toInt()

                //println("삽입할 id : ${insertId}, 삽입 위치 : ${insertIdx}")
                if(insertIdx >= 0)
                    videoLinkedList.insertData(insertId, insertIdx)
                else
                    println("위치값은 0 이상이어야 합니다")
            }
            "delete" -> {
                var deleteId = command.nextToken()
                videoLinkedList.deleteData(deleteId)
            }
            "render" -> {
                videoLinkedList.renderData()
            }
            "quit" -> {
                println("편집을 종료합니다.")
                break
            }
            else -> {
                println("올바르지 않은 명령어 입니다.")
            }
        }

    }
}

class VideoLinkedList(videoDataList: MutableList<VideoData>){

    var videoDataList = videoDataList
    var Head = VideoData("head", -1, -1)  // 가장 처음이 되는 노드를 Head로 두었다(출력은 무시)

    // 마지막 다음에 삽입하기
    fun addData(addId: String){ // add할 id 전달받기
        if(checkExist(addId)){

            var node : VideoData? = Head
            // Head에서 마지막 노드까지 이동
            while(node?.nextData != null){
                node = node.nextData
            }

            node?.nextData = getVideoById(addId)
            node?.nextData?.preData = node  // 새로 추가된 노드의 전 노드는 기존의 마지막 노드!
            printCurrentState()
        }
        else
            printNotExistError()
    }

    fun deleteData(deleteId: String){
        // 삭제의 경우 연결리스트에 지우려는 것이 존재하는지 살펴야함 - 추가와 차이점!
        if(checkIsExistInLinkedList(deleteId)){
            var node: VideoData? = Head
            while(node?.nextData != null){
                node = node.nextData

                if(deleteId.equals(node?.id)){
                    node?.preData?.nextData = node?.nextData
                    node?.nextData?.preData = node?.preData
                }
            }
            printCurrentState()
        }
        else
            printNotExistError()
    }

    fun insertData(insertId: String, insertIdx: Int){
        if(checkExist(insertId)){
            var node : VideoData? = Head
            var idxCount = 0
            var isInsertIdxBigger = true

            while(node?.nextData != null){
                if(idxCount == insertIdx){
                    var insertNode = getVideoById(insertId)
                    insertNode?.preData = node
                    insertNode?.nextData = node.nextData

                    node?.nextData?.preData = insertNode
                    node?.nextData = insertNode
                    isInsertIdxBigger = false
                    break
                }
                node = node.nextData
                idxCount++
            }

            if(isInsertIdxBigger){ // 순서값이 현재 연결 리스트 데이터 개수보다 같거나 많은 경우 -> 맨뒤에 삽입
                node?.nextData = getVideoById(insertId)
                node?.nextData?.preData = node
            }

            printCurrentState()
        }
        else
            printNotExistError()
    }

    fun renderData(){
        //Head 다음 것 부터 계산!!
        var node : VideoData? = Head
        var count = 0
        var totalSec = 0

        while(node?.nextData != null){
            node = node.nextData

            count++
            totalSec += node!!.playTime
        }

        printCurrentState()
        println("영상클립 : ${count}개")
        println("전체길이 : ${totalSec}sec")
    }

    fun printCurrentState(){
        print("|")

        var node : VideoData? = Head
        while(node?.nextData != null){
            node = node.nextData

            print("---[${node!!.id}, ${node!!.playTime}sec]")
        }

        println("---[end]")
    }

    ///////////////////
    fun checkExist(id: String): Boolean{
        videoDataList.forEach {
            if(id.equals(it.id))
                return true
        }
        return false
    }

    fun checkIsExistInLinkedList(id: String) : Boolean{
        var node: VideoData? = Head
        while(node?.nextData != null){
            node = node.nextData
            if(id.equals(node?.id)){
                return true
            }
        }
        return false
    }

    fun printNotExistError(){
        println("존재하지 않는 데이터입니다.")
    }

    fun getVideoById(id: String) : VideoData?{
        videoDataList.forEach {
            if(id.equals(it.id)){
                it.clearNextAndPre()
                return it
            }
        }
        return null
    }
}

class VideoData(id: String, playTime: Int, idNum: Int ){
    var id = id
    var playTime = playTime
    var idNum = idNum
    var nextData : VideoData? = null
    var preData : VideoData? = null

    fun printData(){
        println("제목${idNum}(${id}):${playTime}")
    }

    fun clearNextAndPre(){
        nextData = null
        preData = null
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
}