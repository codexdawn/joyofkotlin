package chapter3

class Functions {
    var percent1 = 5
    private var percent2 = 9
    val percent3 = 13

    fun add(a:Int, b:Int) = a + b  // YES! Pure function
    fun multply(a:Int, b:Int?) = 5 // YES! 인자로 어떤값을 받든지 관계없이 같은 값을 반환한다. 상수함수!
    fun div(a:Int, b:Int) = a / b // NO! b 가 0일때 예외가 발생 한다! 예외를 던지기때문에 not Pure function
    fun div(a:Double, b:Double) = a / b // YES! 0.0으로 나누면 예외발생안하고, Infinity or -Infinity가 반환된다.
    fun applyTax1(a:Int) = a / 100 * (100 + percent1) // NO! percent1 으로 내부동작을 제어하여 관찰함
    fun applyTax1FixedPureFunction(fs:Functions, a:Int):Int = a/100 * (100 + fs.percent1) // YES! 순수함수
    fun applyTax2(a:Int) = a / 100 * (100 + percent2) // NO! percent2는 비공개변수지만, 결국은 위와 동일하게 외부에서 내부를 간섭한다.
    fun applyTax3(a:Int) = a / 100 * (100 + percent3) // YES! percent3는 불변이라서 내부에서 변이가 발생하지는 않아서 pure function!

    // NO! list 가변리스트
    fun append(i:Int, list:MutableList<Int>): List<Int> {
        list.add(i)
        return list
    }

    fun append2(i:Int, list:List<Int>) = list + i // YES! list가 불변리스트 기 때문에
}

