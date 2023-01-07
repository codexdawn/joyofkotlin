package chapter3

typealias IntBinOp = (Int) -> (Int) -> Int
typealias IntUaryOp = (Int) -> Int
fun main() {
    //val compose = compose({x:Int -> sqaure(x) }, {y:Int -> triple(y)})
    val compose = compose(::sqaure, ::triple)
    println(compose(3))

    val add:IntBinOp = {n -> {x -> x + n}}
    println(add(4)(4))
    println(add1()(3)(4))
    val compose1: (IntUaryOp) -> (IntUaryOp) -> IntUaryOp = {x -> {y -> {z -> x(y(z))}}}
    val square:IntUaryOp = {it * it}
    val triple:IntUaryOp = {it * 3}
    val squareOfTriple = compose1(square)(triple)
    println(squareOfTriple(3))
    val squareOfTripeHighOrder = higherCompose2<Int, Int, Int>()(square)(triple)
    println(squareOfTripeHighOrder(3))
    val tripleOfSquare = higherAndThen<Int,Int,Int>()(triple)(square)
    println(tripleOfSquare(3))
}

fun compose1(x:(Int) -> Int, y:(Int) -> Int): (Int) -> Int = {n-> x(y(n))}

fun <T,U,V> compose(x:(U) -> V, y:(T) -> U): (T) -> V = {n -> x(y(n))}
fun sqaure(n:Int) = n * n

fun triple(n:Int) = n * 3

fun add1():IntBinOp = {n -> {x -> x + n}}

fun <T,U,V> higherCompose():((U) -> V)-> ((T) -> U) -> (T) -> V =
    {x ->
        {y ->
            { n -> x(y(n)) }
        }
    }

fun <T,U,V> higherCompose2() =
    {x:(U) -> V -> {y:(T) -> U -> {n:T -> x(y(n))}}}

fun <T,U,V> higherAndThen() =
    {x:(T) -> U -> {y:(U) -> V -> {n:T -> y(x(n))}}}