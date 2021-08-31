package com.ridwanstandingby.ntris.ui.leaderboard

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LeaderboardActivity : AppCompatActivity() {

    private val leaderboardViewModel: LeaderboardViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LeaderboardActivityUi(leaderboardViewModel)
        }
        leaderboardViewModel.start()
    }
}
