package com.ridwanstandingby.ntris.activities

import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.ridwanstandingby.ntris.Application
import com.ridwanstandingby.ntris.NTrisApplication

class GameActivity : AndroidApplication() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = AndroidApplicationConfiguration()
        config.useWakelock = true

        initialize(NTrisApplication((application as Application).dataManager), config)
    }
}
