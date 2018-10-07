package com.ridwanstandingby.ntris

import android.os.Bundle

import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.ridwanstandingby.ntris.data.AndroidDataManager

class AndroidLauncher : AndroidApplication() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = AndroidApplicationConfiguration()
        config.useWakelock = true

        val dataManager = AndroidDataManager(this)
        dataManager.loadPolyominoBlueprints()

        initialize(NTrisApplication(dataManager), config)
    }
}
