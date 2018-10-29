package com.ridwanstandingby.ntris

import android.app.Application
import com.ridwanstandingby.ntris.data.AndroidGameDataManager
import com.ridwanstandingby.ntris.data.GameDataManager
import com.ridwanstandingby.ntris.data.SharedPreferencesManager
import com.ridwanstandingby.ntris.data.remote.RemoteDataManager

class Application : Application() {

    lateinit var gameDataManager: GameDataManager
    lateinit var sharedPreferencesManager: SharedPreferencesManager
    lateinit var remoteDataManager: RemoteDataManager

    override fun onCreate() {
        super.onCreate()
        remoteDataManager = RemoteDataManager()
        sharedPreferencesManager = SharedPreferencesManager(this)
        gameDataManager = AndroidGameDataManager(this, sharedPreferencesManager)
    }
}
