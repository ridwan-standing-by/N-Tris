package com.ridwanstandingby.ntris.activities.leaderboard

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.ridwanstandingby.ntris.Application
import com.ridwanstandingby.ntris.R
import kotlinx.android.synthetic.main.layout_leaderboard.*

class LeaderboardActivity : AppCompatActivity() {

    private val remoteDbManager by lazy { (application as Application).remoteDataManager }

    private lateinit var scoreEntryAdapter: ScoreEntryAdapter
    private lateinit var leaderboardViewModel: LeaderboardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_leaderboard)

        initRecyclerView()
        initLeaderboardViewModel()
    }

    private fun initRecyclerView() {
        scoreEntryAdapter = ScoreEntryAdapter(this)
        leaderboardRecyclerView.layoutManager = LinearLayoutManager(this)
        leaderboardRecyclerView.adapter = scoreEntryAdapter
    }

    private fun initLeaderboardViewModel() {
        leaderboardViewModel = ViewModelProviders.of(this).get(LeaderboardViewModel::class.java)
        leaderboardViewModel.getScoreEntries(remoteDbManager).observe(this, Observer { list ->
            if (list != null) {
                scoreEntryAdapter.items = list.sortedByDescending { it.score }
                scoreEntryAdapter.notifyDataSetChanged()
            }
        })
    }
}
