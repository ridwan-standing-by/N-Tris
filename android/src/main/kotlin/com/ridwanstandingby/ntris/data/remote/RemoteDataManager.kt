package com.ridwanstandingby.ntris.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.ridwanstandingby.ntris.data.remote.models.FirestoreScoreEntry
import com.ridwanstandingby.ntris.data.remote.models.toDomainScoreEntry
import com.ridwanstandingby.ntris.data.remote.models.toFirestoreScoreEntry
import com.ridwanstandingby.ntris.domain.ScoreEntry

class RemoteDataManager {

    private val db = FirebaseFirestore.getInstance()

    /** @throws UploadScoreEntryFailureException */
    fun uploadScoreEntry(scoreEntry: ScoreEntry,
                         onError: (Throwable) -> Unit = { throw it }) {
        db.collection(SCORES_COLLECTION_KEY)
                .add(scoreEntry.toFirestoreScoreEntry())
                .addOnFailureListener { onError(UploadScoreEntryFailureException(it)) }
    }

    class UploadScoreEntryFailureException(cause: Exception?) : Exception(cause)

    /** @throws DownloadScoreEntryFailureException */
    fun downloadScoreEntries(onSuccess: (List<ScoreEntry>) -> Unit = {},
                             onError: (Throwable) -> Unit = { throw it }) {
        db.collection(SCORES_COLLECTION_KEY)
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
    }
}
