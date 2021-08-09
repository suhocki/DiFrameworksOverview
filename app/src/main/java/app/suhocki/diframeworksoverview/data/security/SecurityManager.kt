package app.suhocki.diframeworksoverview.data.security

import app.suhocki.diframeworksoverview.data.device.DeviceController
import toothpick.InjectConstructor
import java.util.*

@InjectConstructor
class SecurityManager(
    private val deviceController: DeviceController,
) {

    fun generateSecurityHash(): String {
        val randomId = UUID.randomUUID()

        return "$randomId:${deviceController.getDeviceId()}"
    }
}