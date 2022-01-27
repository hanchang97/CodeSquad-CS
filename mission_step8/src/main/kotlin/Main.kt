import kotlinx.coroutines.*

fun main(args: Array<String>) {
    testCoroutine1()
}

fun testCoroutine1() = runBlocking {
    CoroutineScope(Dispatchers.Default)
        .launch {
            delay(1000)
            println("1초 후 출력")
        }

    println("먼저 출력")
    delay(1500)
}

// runBlocking -> 코루틴의 실행이 끝날 때까지 현재 스레드를 블록 -> main()이 바로 끝나면서 메인스레드가 종료되어 프로그램 전체가 끝나버리는 것 방지
// CoroutineScope 지정 -> 내가 스레드를 사용하겠다
