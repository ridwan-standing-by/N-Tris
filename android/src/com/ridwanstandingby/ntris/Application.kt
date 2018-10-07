package com.ridwanstandingby.ntris

import android.app.Application
import com.ridwanstandingby.ntris.data.AndroidDataManager
import com.ridwanstandingby.ntris.data.DataManager

class Application : Application() {

    lateinit var dataManager: DataManager

    override fun onCreate() {
        super.onCreate()
        dataManager = AndroidDataManager(this)
    }
}