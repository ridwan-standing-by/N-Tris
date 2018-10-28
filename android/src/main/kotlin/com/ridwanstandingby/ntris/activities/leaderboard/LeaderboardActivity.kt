package com.ridwanstandingby.ntris.activities.leaderboard

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class LeaderboardActivity : AppCompatActivity() {

    private lateinit var leaderboardViewModel: LeaderboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        leaderboardViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
                .create(LeaderboardViewModel::class.java)
    }
}
