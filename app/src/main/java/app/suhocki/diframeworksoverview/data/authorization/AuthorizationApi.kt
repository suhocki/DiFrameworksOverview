package app.suhocki.diframeworksoverview.data.authorization

import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

class AuthorizationApi {
    /**
     * @return security token if authorization finished successfully.
     */
    suspend fun authorize(user: String, secureHash: String): String {
        delay(5000)
        coroutineContext.ensureActive()
        return (user + secureHash).hashCode().toString()
    }
}