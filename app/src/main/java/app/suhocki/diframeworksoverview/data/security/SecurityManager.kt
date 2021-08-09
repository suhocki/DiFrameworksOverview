package app.suhocki.diframeworksoverview.data.security

import app.suhocki.diframeworksoverview.data.device.DeviceController
import java.util.*

class SecurityManager(
    private val deviceController: DeviceController,
) {

    fun generateSecurityHash(): String {
        val randomId = UUID.randomUUID()

        return "$randomId:${deviceController.getDeviceId()}"
    }
}