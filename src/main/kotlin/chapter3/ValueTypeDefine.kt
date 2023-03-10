package chapter3

data class Price private constructor(private val value:Double) {
    operator fun plus (price: Price) = Price(this.value + price.value)

    operator fun times(num:Int) = Price(this.value * num)

    companion object {
        val identity = Price(0.0)
        operator fun invoke(value: Double):Price =
            if (value > 0) {
                Price(value)
            } else {
                throw IllegalArgumentException("Price must be positive or null")
            }
    }
}

data class Weight private constructor(private val value:Double) {
    operator fun plus(weight: Weight) = Weight(this.value + weight.value)

    operator fun times(num:Int) = Weight(this.value * num)

    companion object {
        val identity = Weight(0.0)

        operator fun invoke(value: Double):Weight =
            if (value > 0) {
                Weight(value)
            } else {
                throw IllegalArgumentException("Weight must be positive or null")
            }

    }
}

data class Product(val name:String, val price:Price, val weight: Weight)

data class OrderLine(val product: Product, val count:Int) {
    fun weight() = product.weight * count
    fun amount() = product.price * count
}

object Store {
    @JvmStatic
    fun main() {
        val toothPaste = Product("Tooth Paste", Price(1.5), Weight(0.5))
        val toothBrush = Product("Tooth Brush", Price(3.5), Weight(0.3))

        val orderLines = listOf(
            OrderLine(toothPaste,3)
            , OrderLine(toothBrush,2)
        )

        val weight:Weight = orderLines.fold(Weight.identity) { a, b -> a + b.weight()}
        val price:Price = orderLines.fold(Price.identity) {a , b -> a + b.amount()}

        println("Total Price : $price")
        println("Total Weight : $weight")


    }
}