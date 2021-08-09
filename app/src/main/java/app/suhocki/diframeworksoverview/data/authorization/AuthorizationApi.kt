package app.suhocki.diframeworksoverview.data.authorization

import kotlinx.coroutines.delay
import toothpick.InjectConstructor

@InjectConstructor
class AuthorizationApi {
    /**
     * @return security token if authorization finished successfully.
     */
    suspend fun authorize(user: String, secureHash: String): String {
        delay(5000)

        return (user + secureHash).hashCode().toString()
    }
}