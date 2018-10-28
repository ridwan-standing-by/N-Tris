package com.ridwanstandingby.ntris.activities.leaderboard

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.ridwanstandingby.ntris.data.remote.RemoteDataManager
import com.ridwanstandingby.ntris.domain.ScoreEntry

class LeaderboardViewModel : ViewModel() {

    val scoreEntries: MutableLiveData<List<ScoreEntry>> = MutableLiveData()

    fun getScoreEntries(remoteDataManager: RemoteDataManager): LiveData<List<ScoreEntry>> {
        if (scoreEntries.value == null) {
            remoteDataManager.downloadScoreEntries(
                    onSuccess = { scoreEntries.postValue(it) },
                    onError = { it.printStackTrace() })
        }
        return scoreEntries
    }
}
