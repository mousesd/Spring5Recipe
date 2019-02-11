package app03

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class PlayerSpec extends Specification implements DomainUnitTest<Player> {

    def setup() {
    }

    def cleanup() {
    }

    void validPlayerIsConstructed() {
        given:
            def player = new Player(name: 'James', phone: '120-111')
        when: "validate is called"
            def result = player.validate()
        then: "it should be valid"
            result
    }

    void playerWithoutANameIsConstructed() {
        given:
            def player = new Player(name: '', phone: '120-111')
        when: "validate is called"
            def result = player.validate()
        then: "The name should be rejected"
            !result
            player.errors['name'].codes.contains('nullable')
    }

    void playerWithoutAPhoneIsConstructed() {
        given:
            def player = new Player(name: 'James', phone: '')
        when: "validate is called"
            def result = player.validate()
        then: "The phone number should be rejected"
            !result
            player.errors['phone'].codes.contains('nullable')
    }
}
