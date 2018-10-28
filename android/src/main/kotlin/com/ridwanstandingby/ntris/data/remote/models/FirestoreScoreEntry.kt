package com.ridwanstandingby.ntris.data.remote.models

import com.google.firebase.Timestamp
import com.ridwanstandingby.ntris.domain.ScoreEntry

class FirestoreScoreEntry(var time: Timestamp,
                          var name: String,
                          var score: Long,
                          var lines: Long)

fun FirestoreScoreEntry.toDomainScoreEntry() = ScoreEntry(
        timeStamp = time.seconds,
        name = name,
        score = score,
        lines = lines)

fun ScoreEntry.toFirestoreScoreEntry() = FirestoreScoreEntry(
        time = Timestamp(timeStamp / MILLIS_IN_SECOND,
                (timeStamp % MILLIS_IN_SECOND).toInt() * NANOS_IN_MILLI),
        name = name,
        score = score,
        lines = lines)

private const val MILLIS_IN_SECOND = 1000
private const val NANOS_IN_MILLI = 1000000
