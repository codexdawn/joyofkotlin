package chapter3

import chapter1.donut.CreditCard
import chapter3.Payment2.Companion.combine
import java.math.BigDecimal

fun main() {
    val creditCard = CreditCard()
    val payment1 = Payment2(creditCard, BigDecimal(3000))
    val payment2 = Payment2(creditCard, BigDecimal(5000))
    val payment3 = Payment2(creditCard, BigDecimal(7000))

    val objectNewPayment = payment1.combine(payment2).combine(payment3) //기존 자바나 객체지향으로 인스턴스를 받아서 계산하는 방식
    val newPayment = combine(combine(payment1, payment2), payment3) //동반객체로 함수를 빼서 사용하는 방식

    println(objectNewPayment)
    println(newPayment)

    val double2: (Int) -> Int = {x -> x * 2}

    println(double2(4))
    println(double1(4))

    val add: (Int,Int) -> Int = {x,y -> x + y}
    println(add(4,3))

    val multiplyBy2: (Int) -> Int = {x -> double1(x) }
    println(multiplyBy2(3))

}
fun double1(x:Int): Int = x * 2


