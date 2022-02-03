import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {

    //testCoroutine1()

    /*runBlocking {
        val a = launch {
            (1..5).forEach {
                println(it)
                delay(15)
            }
        }

        val b = async {
            "async 종료"
        }

        println("async 대기")
        println(b.await())

        println("launch 대기")
        a.join()
        println("launch 종료")
    }*/

    // withTimeoutOrNullTest()

    //suspendTest1()
    //suspendTest2()

    GlobalScope.launch {
        delay(1000L)
        println("World")
    }
    println("Hello")

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

fun withTimeoutOrNullTest() = runBlocking {
    var result = withTimeoutOrNull(50){
        (1..10).forEach {
            println(it)
            delay(10)
        }
        "Finish"
    }
    println(result)  // timeout 내에 실행이 불가해 null 이 출력된다
}

/////////////////////////////////////////////////////////////////////

fun suspendTest1() = runBlocking {
    // 블록 내부 실행 시간 알 수 있다
    val time = measureTimeMillis {
        val one = suspend1()
        val two = suspend2()
        println("sum answer = ${one + two}")
    }
    println("Completed in $time ms")
}

fun suspendTest2() = runBlocking {
    // 블록 내부 실행 시간 알 수 있다
    val time = measureTimeMillis {
        val one = async { suspend1() }  // 비동기로 처리할 수 있게 async 코루틴 빌더 사용
        val two = async { suspend2() }
        println("sum answer = ${one.await() + two.await()}")  // 결과오기 전까지 print되지 않는다
    }
    println("Completed in $time ms")
    // main, suspend1, suspend2 모두 Main Thread 안에서 실행
    // suspendTest2 에서는 동시성 제공
}

suspend fun suspend1(): Int{
    delay(1000L)  // dealay 대신 Thread.sleep(1000L)을 하면 스레드 자체가 중단되서 결과적으로 2000ms 의 값이 나오게 된다
    return 10
}

// delay의 경우는 아무것도 하지 않고 있는 상태를 유지하는 일을 하고 있다고 생각하면 된다 / sleep은 스레드 전체를 그냥 중단
suspend fun suspend2(): Int{
    delay(1000L)  // dealay 대신 Thread.sleep(1000L)을 하면 스레드 자체가 중단되서 결과적으로 2000ms 의 값이 나오게 된다
    return 15
}