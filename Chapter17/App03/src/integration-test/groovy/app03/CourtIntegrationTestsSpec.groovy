package app03

import grails.testing.mixin.integration.Integration
import grails.transaction.*
import spock.lang.Specification

@Integration
@Rollback
class CourtIntegrationTestsSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void testQueries() {
        given: "2 Existing Players"
            def players = [new Player(name: 'James', phone: '120-1111')
                         , new Player(name: 'Martha', phone: '999-9999')]
            players*.save()
            Player.list().size() == 2

        when: "Player James is retrieved"
            def testPlayer = Player.findByName("James")

        then: "The phone number should match"
            testPlayer.phone == "120-1111"

        when: "Player James is updated"
            testPlayer.name = "Marcus"
            testPlayer.save()

        then: "The name should be updated in the DB"
            def updatedPlayer = Player.findByPhone("120-1111")
            updatedPlayer.name == "Marcus"

        when: "The updated player is deleted"
            updatedPlayer.delete()

        then: "The player should be removed from the DB"
            Player.list().size() == 1
            def nonExistPlayer = Player.findByPhone("120-1111")
            nonExistPlayer == null
    }
}
