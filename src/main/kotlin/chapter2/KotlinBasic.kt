package chapter2

import java.time.LocalDateTime

fun main() {

    /**
     * 코틀린에서도 타입추론을 많이 권장하는듯하나, 정말 명확한 타입 추측이 가능하다면 타입추론을 사용하되 애매모호 하다 싶으면 명시하는것이 좋을것같다.
     */
    val lastName: String = "Dawn" // 기본 변수 세팅
    val firstName = "Sim" // 타입추론 적용 및 불변


    /**
     * 가급적이면 var를 사용하기보다 val을 사용하도록 습관화 하자.
     *  - val 은 참조가 불변이다만, 모든 참조가 항상 불변은 아니다. 하지만 가능한 val을 애용해야한다.
     *  - 최대한 빨리 참조를 초기화 하도록 해야한다.
     */
    var age:Int = 38
    age = 36

    log("firstName : $firstName \nlastName : $lastName \nage: $age")

    /**
     * 코틀린에서의 null 타입 제어 방법
     *  - null 타입은 ? 타입뒤 연산을 통해 명시한다.
     *  - null 타입 상태에서는 초기화가 쉽지 안된다. 아래 코드처럼 getCompany 함수를 호출한다면 초기화는 안된다.
     *  - 아래 예시는 좋은 예시가 아니다. 불변으로 가져가는것이 좋다!
     */
    var company:String? = null
    company = getCompany()
    log("company : $company")

    /**
     * 지연초기화 방법
     * - 타입뒤 by lazy { getCompany() } 요런식으로 하면 지연초기화 할수있음
     * - var 상태에서도 지연초기화가 가능한데, lateinit var name: String ... name = getName() 요런식으로 가능하지만 가급적 by lazy에 비해 전혀 장점이 없다. 그리고 DI 에서 불변 프로퍼티를 주로 사용하기에.. 더욱더 사용할 이유가 없음.
     *
     * --출력 결과 --
     * 지연 초기화 하이!
     * 안녕하세요!
     * 신규 회사 체크중...
     * newCompany: Musinsa
     * 미래 회사 체크중...
     * futureCompany: Naver
     * newCompany: Musinsa
     * futureCompany: Naver
     *
     * - 지연초기화는 실제 프로퍼티가 사용되는 구간에서 해당 함수를 호출 하여 초기화 한다.
     * - 한번 지연초기화 되면 마치 캐싱된것처럼 참조값만 불러온다. 두번째 호출부터는 내부에 println 찍은건 미출력됨.
     *
     */
    val newCompany:String by lazy(::getNewCompany)
    log("지연 초기화 하이!")
    val futureCompany:String by lazy {getFutureCompany()}
    log("안녕하세요!")
    log("newCompany: $newCompany")
    log("futureCompany: $futureCompany")
    log("newCompany: $newCompany")
    log("futureCompany: $futureCompany")

    //클래스 인스턴스 화
    val person = Person("Dawn")
    log("person : ${person.name}")

    //데이터 객체 구조 분해하기
    val companies = listOf<Company>(Company("Apple", "U.S SA"), Company("Naver", "KR GG"))
    show(companies)

    println(DateTimeUtils.toDate(LocalDateTime.now()))

    println(generateUUID())

    /**
     * 코틀린은 기본적으로 불변리스트를 사용한다. 하지만, 원소 변경이 안되는것이지, 완전한 불변 리스트는 아니고, 읽기 전용 컬렉션이라고 부르는것이 맞는듯하다.
     */
    val numbers1 = listOf(1, 2, 3)
    val numbers2 = numbers1 + listOf(4)
    val numbers3 = numbers1 + numbers2
    println(numbers1)
    println(numbers2)
    println(numbers3)

    val mutableNumbers1 = mutableListOf(1,2,3)
    val mutableNumbers2 = mutableNumbers1.add(4)
    val mutableNumbers3 = mutableNumbers1.addAll(mutableNumbers1)
    println(mutableNumbers1)
    println(mutableNumbers2)
    println(mutableNumbers3)


}

//객체 구조 분해
fun show(companies : List<Company>) {
    for ((name, loc) in companies) {
        println("$name's location $loc")
    }
}

fun getFutureCompany():String {
    println("미래 회사 체크중...")
    return "Naver"
}

fun getNewCompany():String {
    println("신규 회사 체크중...")
    return "Musinsa"
}

fun getCompany(): String? {
    println("회사 체크중...")
    return "29cm"
}

fun log(log:String):Unit = println(log)