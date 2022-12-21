package chapter2

import java.time.LocalDate
import java.time.LocalDateTime

/**
 * 생성 비용이 큰 객체 이거나 유틸 클래스 같은 한번 생성해 놓고 사용하는 객체는 object 클래스를 고려해볼만하다.
 */
object DateTimeUtils {
    fun toDate(datetime: LocalDateTime) : LocalDate {
        return datetime.toLocalDate()
    }
}