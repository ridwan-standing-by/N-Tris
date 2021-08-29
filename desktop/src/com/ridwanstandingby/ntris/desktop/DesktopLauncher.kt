@file:Suppress("unused")

package com.ridwanstandingby.ntris.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.ridwanstandingby.ntris.NTrisApplication
import com.ridwanstandingby.ntris.desktop.data.DesktopGameDataManager

object DesktopLauncher {
    @JvmStatic
    fun main() {
        val config = LwjglApplicationConfiguration()
        config.width = 393
        config.height = 660

        val dataManager = DesktopGameDataManager()

        LwjglApplication(NTrisApplication(dataManager), config)
    }
}
