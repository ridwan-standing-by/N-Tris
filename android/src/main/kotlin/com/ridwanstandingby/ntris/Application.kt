package com.ridwanstandingby.ntris

import android.app.Application
import com.ridwanstandingby.ntris.data.AndroidGameDataManager
import com.ridwanstandingby.ntris.data.GameDataManager

class Application : Application() {

    lateinit var gameDataManager: GameDataManager

    override fun onCreate() {
        super.onCreate()
        gameDataManager = AndroidGameDataManager(this)
    }
}