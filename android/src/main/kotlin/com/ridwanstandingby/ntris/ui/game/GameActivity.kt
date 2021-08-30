package com.ridwanstandingby.ntris.ui.game

import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.ridwanstandingby.ntris.NTrisApplication
import com.ridwanstandingby.ntris.data.GameDataManager
import org.koin.android.ext.android.inject

class GameActivity : AndroidApplication() {

    private val gameDataManager: GameDataManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = AndroidApplicationConfiguration()
        config.useWakelock = true

        initialize(NTrisApplication(gameDataManager), config)
    }
}
