package com.ridwanstandingby.ntris.activities.leaderboard

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ridwanstandingby.ntris.domain.ScoreEntry

class LeaderboardViewModel : ViewModel() {

    var scoreEntries: MutableLiveData<List<ScoreEntry>> = MutableLiveData()

    fun getScoreEntries(): LiveData<List<ScoreEntry>> {
        if (scoreEntries.value == null) {
            FirebaseDatabase.getInstance
        }
        return scoreEntries
    }
}
