package com.ridwanstandingby.ntris.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.ridwanstandingby.ntris.NTrisApplication
import com.ridwanstandingby.ntris.desktop.data.DesktopDataManager

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.width = 393
        config.height = 660

        val dataManager = DesktopDataManager()

        LwjglApplication(NTrisApplication(dataManager), config)
    }
}
