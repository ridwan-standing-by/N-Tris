package com.ridwanstandingby.ntris

import android.support.multidex.MultiDexApplication
import com.ridwanstandingby.ntris.data.AndroidGameDataManager
import com.ridwanstandingby.ntris.data.GameDataManager
import com.ridwanstandingby.ntris.data.local.SharedPreferencesManager
import com.ridwanstandingby.ntris.data.remote.RemoteDataManager

class Application : MultiDexApplication() {

    lateinit var gameDataManager: GameDataManager
    lateinit var sharedPreferencesManager: SharedPreferencesManager
    lateinit var remoteDataManager: RemoteDataManager

    override fun onCreate() {
        super.onCreate()
        remoteDataManager = RemoteDataManager()
        sharedPreferencesManager = SharedPreferencesManager(this)
        gameDataManager = AndroidGameDataManager(this, sharedPreferencesManager, remoteDataManager)
    }
}
