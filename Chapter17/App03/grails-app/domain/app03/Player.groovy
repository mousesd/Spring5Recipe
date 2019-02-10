package app03

class Player {
    String name
    String phone

    static hasMany = [reservations: Reservation]
    static constraints = {
        name(blank: false)
        phone(blank: false)
    }
}
