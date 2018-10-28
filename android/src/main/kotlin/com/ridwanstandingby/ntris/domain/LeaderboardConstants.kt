package com.ridwanstandingby.ntris.domain

import java.util.*
import java.util.concurrent.TimeUnit

object LeaderboardConstants {

    val ONE_WEEK_AGO = Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(7))
    val BEGINNING_OF_TIME = Date(0L)

    const val SCORE_ENTRY_LIMIT = 1000L
}