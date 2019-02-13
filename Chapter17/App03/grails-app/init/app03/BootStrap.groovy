package app03

class BootStrap {

    def init = { servletContext ->
        def adminRole = new SecRole(authority: "ROLE_ADMIN").save(flush: true)
        def userRole = new SecRole(authority: "ROLE_USER").save(flush: true)
        def testUser = new SecUser(username: "user", password: "password").save(flush: true)
        def testAdmin = new SecUser(username: "admin", password: "password").save(flush: true)
        SecUserSecRole.create(testUser, userRole, true)
        SecUserSecRole.create(testAdmin, adminRole, true)
    }
    def destroy = {
    }
}
