package com.ridwanstandingby.ntris.ui.leaderboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ridwanstandingby.ntris.data.remote.RemoteDataManager
import com.ridwanstandingby.ntris.domain.LeaderboardConstants.BEGINNING_OF_TIME
import com.ridwanstandingby.ntris.domain.LeaderboardConstants.ONE_WEEK_AGO
import com.ridwanstandingby.ntris.domain.LeaderboardConstants.SCORE_ENTRY_LIMIT
import com.ridwanstandingby.ntris.domain.ScoreEntry

class LeaderboardViewModel(private val remoteDataManager: RemoteDataManager) : ViewModel() {

    private val weeklyScoreEntries: MutableLiveData<List<ScoreEntry>> = MutableLiveData()
    private val allTimeScoreEntries: MutableLiveData<List<ScoreEntry>> = MutableLiveData()

    fun getWeeklyScoreEntries(): LiveData<List<ScoreEntry>> {
        if (weeklyScoreEntries.value == null) {
            loadWeeklyScoreEntries()
        }
        return weeklyScoreEntries
    }

    fun getAllTimeScoreEntries(): LiveData<List<ScoreEntry>> {
        if (allTimeScoreEntries.value == null) {
            loadAllTimeScoreEntries()
        }
        return allTimeScoreEntries
    }

    private fun loadWeeklyScoreEntries() {
        remoteDataManager.downloadOrderedScoreEntriesSinceDateLimited(
            since = ONE_WEEK_AGO,
            limit = SCORE_ENTRY_LIMIT,
            onSuccess = { weeklyScoreEntries.postValue(it) },
            onError = { it.printStackTrace(); weeklyScoreEntries.postValue(null) })
    }

    private fun loadAllTimeScoreEntries() {
        remoteDataManager.downloadOrderedScoreEntriesSinceDateLimited(
            since = BEGINNING_OF_TIME,
            limit = SCORE_ENTRY_LIMIT,
            onSuccess = { allTimeScoreEntries.postValue(it) },
            onError = { it.printStackTrace(); allTimeScoreEntries.postValue(null) })
    }
}
