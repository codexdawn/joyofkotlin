package chapter3

fun main() {
    val f: (Double) -> Double = {Math.PI / 2 - it}
    val sin: (Double) -> Double = Math::sin
    val cos:(Double) -> Double = Math::cos

    compose({x:Double -> Math.PI / 2 - x}, Math::sin)(2.0)

    val cos1 = higherCompose2<Double, Double, Double>()(){ x: Double -> Math.PI / 2 - x }(Math::sin)
    val cosValue = cos1(2.0)

    Store.main()
}
