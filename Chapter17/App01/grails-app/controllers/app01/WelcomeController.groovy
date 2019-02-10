package app01

class WelcomeController {
    Date now = new Date()

    def index() {
        [today:now]
    }
}
