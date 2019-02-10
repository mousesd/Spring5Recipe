package app02

import java.time.DayOfWeek
import java.time.LocalDateTime

class Reservation {
    String courtName
    LocalDateTime date
    Player player
    String sportType

    static belongsTo = [Player]
    static constraints = {
        sportType(inList: ["Tennis", "Soccer"])
        date(validator: { val, obj ->
            if (val.getDayOfWeek() == DayOfWeek.SUNDAY && (val.getHour() < 8 || val.getHour() > 22)) {
                return ['invalid.holidayHour']
            } else if (val.getHour() < 9 || val.getHour() > 22) {
                return ['invalid.weekdayHour']
            }
        })
    }
}
