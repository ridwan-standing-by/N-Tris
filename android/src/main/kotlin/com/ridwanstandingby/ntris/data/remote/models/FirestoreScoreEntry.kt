package com.ridwanstandingby.ntris.data.remote.models

import com.google.firebase.Timestamp
import com.ridwanstandingby.ntris.domain.ScoreEntry

class FirestoreScoreEntry(
    var time: Timestamp = Timestamp(0, 0),
    var name: String = "",
    var score: Long = 0,
    var lines: Long = 0
)

fun FirestoreScoreEntry.toDomainScoreEntry() = ScoreEntry(
    time = time.toDate(),
    name = name,
    score = score,
    lines = lines
)

fun ScoreEntry.toFirestoreScoreEntry() = FirestoreScoreEntry(
    time = Timestamp(time),
    name = name,
    score = score,
    lines = lines
)
