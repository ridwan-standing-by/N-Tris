package com.ridwanstandingby.ntris.activities.leaderboard

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ridwanstandingby.ntris.data.remote.RemoteDataManager
import com.ridwanstandingby.ntris.domain.ScoreEntry

class LeaderboardViewModel : ViewModel() {

    private val weeklyScoreEntries: MutableLiveData<List<ScoreEntry>> = MutableLiveData()
    private val allTimeScoreEntries: MutableLiveData<List<ScoreEntry>> = MutableLiveData()

    fun getWeeklyScoreEntries(remoteDataManager: RemoteDataManager): LiveData<List<ScoreEntry>> {
        if (weeklyScoreEntries.value == null) {
            loadWeeklyScoreEntries(remoteDataManager)
        }
        return weeklyScoreEntries
    }

    fun getAllTimeScoreEntries(remoteDataManager: RemoteDataManager): LiveData<List<ScoreEntry>> {
        if (allTimeScoreEntries.value == null) {
            loadAllTimeScoreEntries(remoteDataManager)
        }
        return allTimeScoreEntries
    }

    private fun loadWeeklyScoreEntries(remoteDataManager: RemoteDataManager) {
        remoteDataManager.downloadScoreEntries(
                onSuccess = { weeklyScoreEntries.postValue(it) },
                onError = { it.printStackTrace() })
    }

    private fun loadAllTimeScoreEntries(remoteDataManager: RemoteDataManager) {
        remoteDataManager.downloadScoreEntries(
                onSuccess = { allTimeScoreEntries.postValue(it) },
                onError = { it.printStackTrace() })
    }
}
