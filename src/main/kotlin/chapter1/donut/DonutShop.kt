package chapter1.donut

import java.math.BigDecimal

class DonutShop {

    companion object {
        /**
         * 부수효과로 인한 문제
         * - 아래 코드는 테스트가 어려움 (테스트 위해서 은행에 연결해 mock 계정으로 거래 등록 과정 필요)
         *
         * 부수효과 해결 방법
         * - 신용카드로 도넛 가격을 지급하고 싶다면 카드 지급이라는 연산을 나타내는 표현을 반환값에 덧붙이는것뿐
         */
        fun buyDonutsLegacy(creditCard: CreditCard) : Donut {
            val donut = Donut()
            creditCard.charge(Donut.price)
            return donut
        }

        /**
         * 부수효과 제거 버전
         */
        fun buyDonuts(quantity: Int = 1 ,creditCard: CreditCard): Purchase =
            Purchase(donut = List(quantity){
                Donut()
            }, payment = Payment(creditCard, Donut.price.multiply(BigDecimal(quantity))))
    }

}