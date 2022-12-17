package chapter1.donut

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal

class DonutShopTest : FunSpec({

    test("테스트 buyDonuts") {
        val creditCard = CreditCard()
        val purchase = DonutShop.buyDonuts(5,creditCard)

        Donut.price.multiply(BigDecimal(5)) shouldBe purchase.payment.amount
        creditCard shouldBe purchase.payment.creditCard
    }
})
