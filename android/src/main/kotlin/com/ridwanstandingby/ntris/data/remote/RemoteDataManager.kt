package com.ridwanstandingby.ntris.data.remote

import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.Query
import com.ridwanstandingby.ntris.data.remote.models.FirestoreScoreEntry
import com.ridwanstandingby.ntris.data.remote.models.toDomainScoreEntry
import com.ridwanstandingby.ntris.data.remote.models.toFirestoreScoreEntry
import com.ridwanstandingby.ntris.domain.LeaderboardConstants.BEGINNING_OF_TIME
import com.ridwanstandingby.ntris.domain.ScoreEntry
import java.util.*

class RemoteDataManager {

    private val db = FirebaseFirestore.getInstance()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    init {
        setSettingsForTimestamps()
        signInIfNecessary()
    }

    private fun setSettingsForTimestamps() {
        db.firestoreSettings = FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build()
    }

    private fun signInIfNecessary() {
        if (auth.currentUser == null) {
            auth.signInAnonymously()
                    .addOnSuccessListener { Log.d("FirebaseAuth", "Signed in successfully") }
                    .addOnFailureListener { it.printStackTrace() }
        }
    }

    /** @throws UploadScoreEntryFailureException */
    fun uploadScoreEntry(scoreEntry: ScoreEntry,
                         onError: (Throwable) -> Unit = { throw it }) {
        db.collection(SCORES_COLLECTION_KEY)
                .add(scoreEntry.toFirestoreScoreEntry())
                .addOnFailureListener { onError(UploadScoreEntryFailureException(it)) }
    }

    class UploadScoreEntryFailureException(cause: Exception?) : Exception(cause)

    /** @throws DownloadScoreEntryFailureException */
    fun downloadOrderedScoreEntriesSinceDateLimited(since: Date,
                                                    limit: Long,
                                                    onSuccess: (List<ScoreEntry>) -> Unit = {},
                                                    onError: (Throwable) -> Unit = { throw it }) {
        if (since == BEGINNING_OF_TIME) {
            downloadAllOrderedScoreEntriesLimited(limit, onSuccess, onError)
        } else {
            downloadScoreEntriesSinceDateThenOrderAndLimit(since, limit, onSuccess, onError)
        }
    }

    private fun downloadScoreEntriesSinceDateThenOrderAndLimit(since: Date,
                                                               limit: Long,
                                                               onSuccess: (List<ScoreEntry>) -> Unit,
                                                               onError: (Throwable) -> Unit) {
        db.collection(SCORES_COLLECTION_KEY)
                .whereGreaterThan(SCORE_TIME_KEY, Timestamp(since))
                .get()
                .addOnSuccessListener { snapshot ->
                    val scoreEntryList = snapshot.documents
                            .map { document -> document.toObject(FirestoreScoreEntry::class.java) }
                            .map { it?.toDomainScoreEntry() }
                            .filterNotNull()
                            .sortedByDescending { it.score }
                            .take(limit.toInt())
                    onSuccess(scoreEntryList)
                }
                .addOnFailureListener { onError(DownloadScoreEntryFailureException(it)) }
    }

    private fun downloadAllOrderedScoreEntriesLimited(limit: Long,
                                                      onSuccess: (List<ScoreEntry>) -> Unit = {},
                                                      onError: (Throwable) -> Unit = { throw it }) {
        db.collection(SCORES_COLLECTION_KEY)
                .orderBy(SCORE_SCORE_KEY, Query.Direction.DESCENDING)
                .limit(limit)
                .get()
                .addOnSuccessListener { snapshot ->
                    val scoreEntryList = snapshot.documents
                            .map { document -> document.toObject(FirestoreScoreEntry::class.java) }
                            .map { it?.toDomainScoreEntry() }
                            .filterNotNull()
                    onSuccess(scoreEntryList)
                }
                .addOnFailureListener { onError(DownloadScoreEntryFailureException(it)) }
    }

    class DownloadScoreEntryFailureException(cause: Exception?) : Exception(cause)

    companion object {
        private const val SCORES_COLLECTION_KEY = "scores"
        private const val SCORE_TIME_KEY = "time"
        private const val SCORE_SCORE_KEY = "score"
    }
}
