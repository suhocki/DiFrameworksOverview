package app.suhocki.diframeworksoverview.data.error

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class ErrorHandler {
    private val _errorString = MutableSharedFlow<String>()
    val errorString: Flow<String> = _errorString

    fun onError(throwable: Throwable) {
        _errorString.tryEmit(throwable.toString())
    }
}