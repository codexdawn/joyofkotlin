package chapter3

fun main() {
    // addTax 함수는 taxRate변수에 대해 닫혀있다.
    // 클로저는 미사용 추천 ! 대신 모듈화를 더 신경써라
    val taxRate = 0.009
    fun addTax(price: Double) = price + price * taxRate
    println(addTax(300.0))

    //튜플로 받을수있다고 했는데, 이건 그냥 변수를 받은걸로 보임
    fun addTax(taxRate: Double, price: Double) = price + price + taxRate

    val addTaxImprovement = {taxRate: Double, price:Double -> price + price * taxRate}
    println(addTaxImprovement(taxRate,300.0))

    val improvement2 = addTaxImprovement2()(taxRate)(300.0)
    println(improvement2)
    val multiplex = { a: Int -> { b: Int -> a * b}}
    println(curringA(3, multiplex)(4) == multiplex(3)(4)) //부분함수 사용법
    println(curringB(3, multiplex)(4))
    println(func<String,String,String,String>()("A")("B")("C")("D"))
}

fun addTaxImprovement2() = { taxRate: Double ->
    { price: Double ->  price + price * taxRate      }
}

fun <T,U,V> curringA(t1:T, t2:(T) -> (U) -> V): (U) -> V {
    return t2(t1)
}

fun <T,U,V> curringB(t1:U, t2:(T) -> (U) -> V): (T) -> V = {
    t:T -> t2(t)(t1)
}

// fun <A,B,C,D> func(a:A, b:B, c:C, d:D):String = "$a, $b, $c, $d" 를 커리함수로 만들어보자
fun <A,B,C,D> func():(A) -> (B) -> (C) -> (D) -> String = {
    a:A -> {
        b:B -> {
            c:C -> {
                d:D -> "$a, $b, $c, $d"
            }
        }
    }
}

//(A,B) -> C 로 가는 함수를 커리함수로 작성
fun <A,B,C> func2(f:(A,B) -> C):(A) -> (B) -> C = {
    a:A -> {
        b:B -> f(a, b)
    }
}

//커리한 함수의 두인자의 순서를 뒤바꾼 새로운 함수를 반환하는 함수작성 하라
fun <T,U,V> swapArgs(f: (T) -> (U) -> V ): (U) -> (T) -> V = {
    u:U -> {t:T -> f(t)(u)}
}