package app03

class Reservation {
    String courtName
    Date date
    Player player
    String sportType

    static belongsTo = [Player]
    static constraints = {
        sportType(inList: ["Tennis", "Soccer"])
        date(validator: {
            if (it[Calendar.HOUR_OF_DAY] == "SUNDAY" && (it[Calendar.HOUR_OF_DAY] < 8 || it[Calendar.HOUR_OF_DAY] > 2)) {
                return ['invalid.holidayHour']
            } else if (it[Calendar.HOUR_OF_DAY] < 8 || it[Calendar.HOUR_OF_DAY] > 21) {
                return ['invalid.weekdayHour']
            }
        })
    }
}
