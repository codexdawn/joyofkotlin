package chapter2

fun main() {
    val message = Message("aka")
    val payload:Any = message.payload

    val length = if (payload is String)
        payload.length
    else
        -1

    println(length)

    val message2 = Message(123)

    val result:Int = when (val payload2 = message2.payload) {
        is String -> payload2.length
        is Int -> payload2
        else -> -1
    }

    println(result)

    val payload3:String = message.payload as String
    val payload4 = message2.payload as? String //Int로 들어오기때문에 Null로 반환함

    println("payload3 : $payload3 / payload4 : $payload4")

    val testNum1 = 1
    val testNum2 = 1

    println(testNum1 == testNum2) // 구조 동등성
    println(testNum1 === testNum2) //참조 동등성

    val testString1 = "Dawn"
    val testString2 = "Dawn"

    println(testString1 == testString2) // true
    println(testString1 === testString2) // true

    val testObject1 = Message(12)
    val testObject2 = Message(12)

    println(testObject1 == testObject2) //true 해시코드를 재정의하면 true 아니면 false 임!
    println(testObject1 === testObject2) //false 주소값이 다르기때문에
}
