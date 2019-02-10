package app03

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ReservationController {

    ReservationService reservationService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond reservationService.list(params), model:[reservationCount: reservationService.count()]
    }

    def show(Long id) {
        respond reservationService.get(id)
    }

    def create() {
        respond new Reservation(params)
    }

    def save(Reservation reservation) {
        if (reservation == null) {
            notFound()
            return
        }

        try {
            reservationService.save(reservation)
        } catch (ValidationException e) {
            respond reservation.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'reservation.label', default: 'Reservation'), reservation.id])
                redirect reservation
            }
            '*' { respond reservation, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond reservationService.get(id)
    }

    def update(Reservation reservation) {
        if (reservation == null) {
            notFound()
            return
        }

        try {
            reservationService.save(reservation)
        } catch (ValidationException e) {
            respond reservation.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'reservation.label', default: 'Reservation'), reservation.id])
                redirect reservation
            }
            '*'{ respond reservation, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        reservationService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
