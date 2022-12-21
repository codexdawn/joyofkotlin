package chapter2

import java.time.Instant

/**
 * 클래스를 확장해서 사용하려면, 클래스 앞에 open 을 붙여서 사용할수있다.
 * 가급적 open은 정말 상속을 사용해야할때 사용하도록 해야한다.
 * 그래서 기본적으로 코틀린은 기본적으로 final class를 기본정책으로 가져간다.
 */
open class Person(val name: String, val registered:Instant= Instant.now())