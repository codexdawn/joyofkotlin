package chapter2

import java.io.File
import java.time.LocalDateTime

fun main(args:Array<String>) {

    //클로저 사용
    println(sumOfPrimes(10))
    println(sumOfPrimesOutClosure(10))
    val numbers = listOf(1, 2, 3, 4, 5)
    println(multiplyAll(numbers, 2))

    //확장함수
    println(numbers.length())

    println(numbers.product())
    println(numbers.plus())

    val name:String? = getNameInWorkingTime()
    println("working time name length : ${name?.length}")

    for (i in 0 until 10 step 2) {
        println(i)
    }

    /**
     * 비검사 예외
     * 코틀린은 무조건 비검사예외만 발생함
     * 자바와 달리 코틀린 try-catch-finally 는 식으로 리턴됨 (값으로 리턴해야함)
     */

    val parsingNum = try {
        args[0].toInt()
    } catch (e: Exception) {
        0
    }

    println(parsingNum)

    /**
     * 사용한 자원 자동 닫기 (Auto Closable)
     * - sequence 에서 use 밖을 벗어나서 forEach 시키면 안됨. 예외발생함(IOException)
     * - 지연계산 컬렉션이기때문에 한줄한줄 실제로 읽어들일때 수행하는 로직이다. use함수블록을 벗어나는순간 자동으로 자원이 닫히기때문에 Use내에서 모든처리를 다해야함.
     */
    val fileName = "/Users/dawn/workspace/joyofkotlin/src/main/kotlin/chapter2/file.txt"

    File(fileName).inputStream()
        .use { it.bufferedReader()
            .lineSequence()
            .forEach { value -> println(value) }
        }

    /**
     * 한줄씩 받아서 처리하는 방법
     */
    File(fileName).forEachLine { println(it) }
    File(fileName).useLines { println(it) }

}

fun getNameInWorkingTime():String? {
    val hour:Int = LocalDateTime.now().hour

    return if (hour in 9 ..18) {
        "Dawn"
    } else {
        null
    }
}

/**
 * limit 보다 작은 모든 소수의 합계를 구하는 로직
 */
fun sumOfPrimes(limit: Int): Long {
    val seq: Sequence<Long> = sequenceOf(2L) + generateSequence(3L) {
        it + 2
    }.takeWhile {
        it < limit
    }

    // Closure 함수 - 클로저를 적용하면 sumOfPrimes 함수 밖에서는 해당 함수를 사용하지 못한다.
    // 해당 함수는 sumOfPrimes 함수 이외에 사용되는 구간이 없을 가능성이 높기때문에 클로저로 작성하는것이 적합하다.
    fun isPrime(n: Long): Boolean = seq.takeWhile {
        it * it <= n
    }.all {
        n % it != 0L
    }

    return seq.filter(::isPrime).sum()
}

//클로저 함수를 밖으로 적용한 예시
fun sumOfPrimesOutClosure(limit:Int): Long {
    val seq: Sequence<Long> = sequenceOf(2L) + generateSequence(3L) {
        it + 2
    }.takeWhile {
        it < limit
    }

    return seq.filter { value -> isPrime(value,seq) }.sum()
}

fun isPrime(n: Long, seq: Sequence<Long>): Boolean = seq.takeWhile {
    it * it <= n
}.all {
    n % it != 0L
}

/**
 * 클로저 안에 람다
 * - 아래와같은 방식으로 아주 좁은 scope 영역에서만 클로저를 사용해야한다.
 */
fun multiplyAll(list:List<Int>, multiplier:Int): List<Int> = list.map {it * multiplier}

/**
 * 확장함수
 */
fun <T> List<T>.length() = this.size

/**
 * fold 초기값 기준 리스트 내 누산처리 하면서 한개의 값으로 도출해주는 함수
 */
fun List<Int>.plus(): Int = this.fold(0) {a , b ->
    a + b}
fun List<Int>.product(): Int = this.fold(1) {a , b ->
    a * b}

