package com.ridwanstandingby.ntris.activities.leaderboard

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ridwanstandingby.ntris.data.remote.RemoteDataManager
import com.ridwanstandingby.ntris.domain.LeaderboardConstants.BEGINNING_OF_TIME
import com.ridwanstandingby.ntris.domain.LeaderboardConstants.ONE_WEEK_AGO
import com.ridwanstandingby.ntris.domain.LeaderboardConstants.SCORE_ENTRY_LIMIT
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
        remoteDataManager.downloadOrderedScoreEntriesSinceDateLimited(
                since = ONE_WEEK_AGO,
                limit = SCORE_ENTRY_LIMIT,
                onSuccess = { weeklyScoreEntries.postValue(it) },
                onError = { it.printStackTrace(); weeklyScoreEntries.postValue(null) })
    }

    private fun loadAllTimeScoreEntries(remoteDataManager: RemoteDataManager) {
        remoteDataManager.downloadOrderedScoreEntriesSinceDateLimited(
                since = BEGINNING_OF_TIME,
                limit = SCORE_ENTRY_LIMIT,
                onSuccess = { allTimeScoreEntries.postValue(it) },
                onError = { it.printStackTrace(); allTimeScoreEntries.postValue(null) })
    }
}
