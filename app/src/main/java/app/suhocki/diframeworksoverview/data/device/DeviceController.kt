package app.suhocki.diframeworksoverview.data.device

import android.content.ContentResolver
import android.provider.Settings.Secure

class DeviceController(
    private val contentResolver: ContentResolver
) {
    fun getDeviceId() = Secure.getString(contentResolver, Secure.ANDROID_ID)
}