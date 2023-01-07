package chapter3

import chapter1.donut.CreditCard
import java.math.BigDecimal

data class Payment2(val creditCard: CreditCard, val amount:BigDecimal) {
    fun combine(payment: Payment2): Payment2 =
        if (creditCard == payment.creditCard)
            Payment2(payment.creditCard,amount + payment.amount)
        else
            throw IllegalStateException("Cards don't match!")
    companion object {
        fun combine(payment1: Payment2,payment2: Payment2): Payment2 =
            if (payment1.creditCard == payment2.creditCard)
                Payment2(payment1.creditCard,payment1.amount + payment2.amount)
            else
                throw IllegalStateException("Cards don't match!")

        /**
         * 신용카드 그룹별 결제 처리
         */
        fun groupByCard(payments: List<Payment2>): List<Payment2> =
            payments.groupBy { it.creditCard }
                .values
                .map { it.reduce(Payment2::combine)}
    }
}