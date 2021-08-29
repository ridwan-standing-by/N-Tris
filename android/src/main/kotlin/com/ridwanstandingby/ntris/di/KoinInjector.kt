package com.ridwanstandingby.ntris.di

import com.ridwanstandingby.ntris.activities.leaderboard.LeaderboardViewModel
import com.ridwanstandingby.ntris.data.AndroidGameDataManager
import com.ridwanstandingby.ntris.data.GameDataManager
import com.ridwanstandingby.ntris.data.local.SharedPreferencesManager
import com.ridwanstandingby.ntris.data.remote.RemoteDataManager
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object KoinInjector {

    fun buildModule() =
        module {
            defineDomainComponents()
            defineUiComponents()
        }

    private fun Module.defineDomainComponents() {
        single { RemoteDataManager() }
        single { SharedPreferencesManager(androidContext()) }
        single<GameDataManager> { AndroidGameDataManager(androidContext(), get(), get()) }
    }

    private fun Module.defineUiComponents() {
        viewModel { LeaderboardViewModel(get()) }
    }
}
