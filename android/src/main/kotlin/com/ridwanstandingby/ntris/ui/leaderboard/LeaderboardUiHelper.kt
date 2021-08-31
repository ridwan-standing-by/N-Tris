package com.ridwanstandingby.ntris.ui.leaderboard

import androidx.annotation.StringRes
import com.ridwanstandingby.ntris.R

enum class LeaderboardMode {
    WEEKLY, ALL_TIME
}

@StringRes
fun LeaderboardMode.toTabNameStringId() =
    when (this) {
        LeaderboardMode.WEEKLY -> R.string.weekly_tab_title
        LeaderboardMode.ALL_TIME -> R.string.all_time_tab_title
    }
