package chapter1

/**
 * 참조투명성 프로그램 학습
 * 1. 치환모델
 *  - add : 두 정수를 더함
 *  - mult : 두 정수를 곱함
 *
 *  add 함수내 mult 함수를 사용하지만, 그냥 숫자만 넣어도 add가 가지는 기능은 그대로 add 기능의 순수성을 갖는다
 *  하지만 add대신 일반 반환값으로 (ex> mult(2,3) + mult(4,5) ) 가져가면, log 함수를 호출하지 않아서 아무런 로그를 남기지 않기때문에 프로그램의 의미가 바뀐다.
 *  결론은 프로그램의 결과가 달라진다고 볼수도있을것 같다.
 */

fun main() {
    val result:Int = add(mult(2, 3), mult(4, 5))
    println(result)
}

fun add(a: Int, b: Int) : Int {
    log(String.format("Returning ${a + b} as the result of $a + $b"))
    return a + b
}

fun mult(a: Int, b: Int): Int = a * b

fun log(log: String) {
    println(log)
}