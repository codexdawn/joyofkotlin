package chapter2

fun main() {
    val s =  "A String"
    val a: Any = s

    val ls = mutableListOf("A String")
    // val la:MutableList<Any> = ls  이렇게하면 컴파일도 안됨.
    val la = ls + 42
    println(la)

    val laa:MutableList<Any> = mutableListOf()
    addAll(laa,ls)
    println(laa)

    //val bag:Bag<MyClassParent> = createBag()
    val bag2:Boolean = useBag(BagImpl())
    val createBag: Bag<out MyClassParent> = createBag()

}

/**
 * list1은 소비하는 객체 (쓰기)
 * list2는 생산하는 객체 (읽기)
 *
 * 둘중 하나만 in/out 을 가이드해주면 된다. 둘다 정의해줘도 되고, 한쪽만 해주면 보통 나머지도 잘 인식하는것 같다.
 */
fun <T> addAll(list1:MutableList<T>,
               list2:MutableList<out T>) {
    for (element in list2) list1.add(element)
}

open class MyClassParent

class MyClass: MyClassParent()

/**
 * 공변/반공변 둘다 사용하는 방법
 * - 하나의 인터페이스는 보통 하나의 메서드 혹은 하나의 종류의 메서드를 사용하는 경우는 거의 없다. 조회하는 객체, 사용하는 객체 둘다 만드는 경우가 많다.
 * - 만약에 추상화를 더 크게 나눠서 조회용 사용하는 객체용으로 나눠서 인터페이스를 짠다면 인터페이스 자체에서 공변/반공변을 정해줄수도있겠지만, 보통 인터페이스를 설계할때는 같이 많이 쓰니 참고하자
 * - 결국 실제 사용하는 함수에서 직접 공변/반공변 여부를 정해주면 된다.
 */
interface Bag<T> {
    fun get():T
    fun use(t:T):Boolean
}

class BagImpl:Bag<MyClass> {
    override fun get():MyClass {
        return MyClass()
    }

    override fun use(t: MyClass): Boolean {
        return true
    }
}

fun createBag():Bag<out MyClassParent> = BagImpl()

fun useBag(bag:Bag<in MyClass>):Boolean {
    //객체 소비했다고 가정
    return true
}

