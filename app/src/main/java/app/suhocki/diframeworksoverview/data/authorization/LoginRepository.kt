package app.suhocki.diframeworksoverview.data.authorization

import app.suhocki.diframeworksoverview.data.security.SecurityManager
import toothpick.InjectConstructor

@InjectConstructor
class LoginRepository(
    private val authorizationApi: AuthorizationApi,
    private val securityManager: SecurityManager
) {
    suspend fun login(user: String): String {
        val securityHash = securityManager.generateSecurityHash()

        return authorizationApi.authorize(user, securityHash)
    }
}