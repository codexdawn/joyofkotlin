package chapter1.donut

import java.math.BigDecimal

class Payment(val creditCard: CreditCard, val amount:BigDecimal) {

    /**
     * 아래 combine함수는 효율적이지 않음
     * - 여러도넛을 한번에 사는 경우 아래 combine함수가 효율적이지 않다. 그러므로 사용안함
     */
    fun combine(payment: Payment): Payment =
        if (creditCard == payment.creditCard)
            Payment(creditCard, amount + payment.amount)
        else
            throw IllegalStateException("Cards don't match!")

    companion object {
        /**
         * 신용카드 그룹별 결제 처리
         */
        fun groupByCard(payments: List<Payment>): List<Payment> =
            payments.groupBy { it.creditCard }
                .values
                .map { it.reduce(Payment::combine)}
    }
}