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

    private val _weeklyScoreEntries: MutableLiveData<List<ScoreEntry>> = MutableLiveData()
    private val _allTimeScoreEntries: MutableLiveData<List<ScoreEntry>> = MutableLiveData()

    val weeklyScoreEntries: LiveData<List<ScoreEntry>> = _weeklyScoreEntries
    val allTimeScoreEntries: LiveData<List<ScoreEntry>> = _allTimeScoreEntries

    fun start() {
        loadWeeklyScoreEntries()
        loadAllTimeScoreEntries()
    }

    private fun loadWeeklyScoreEntries() {
        remoteDataManager.downloadOrderedScoreEntriesSinceDateLimited(
            since = ONE_WEEK_AGO,
            limit = SCORE_ENTRY_LIMIT,
            onSuccess = { _weeklyScoreEntries.postValue(it) },
            onError = { it.printStackTrace(); _weeklyScoreEntries.postValue(emptyList()) })
    }

    private fun loadAllTimeScoreEntries() {
        remoteDataManager.downloadOrderedScoreEntriesSinceDateLimited(
            since = BEGINNING_OF_TIME,
            limit = SCORE_ENTRY_LIMIT,
            onSuccess = { _allTimeScoreEntries.postValue(it) },
            onError = { it.printStackTrace(); _allTimeScoreEntries.postValue(emptyList()) })
    }
}
