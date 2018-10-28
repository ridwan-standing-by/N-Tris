package com.ridwanstandingby.ntris.activities.leaderboard

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.ridwanstandingby.ntris.Application
import com.ridwanstandingby.ntris.R
import kotlinx.android.synthetic.main.layout_leaderboard.*

class LeaderboardActivity : AppCompatActivity() {

    private val remoteDbManager by lazy { (application as Application).remoteDataManager }

    private val leaderboardViewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
                .create(LeaderboardViewModel::class.java)
    }

    private val scoreEntryAdapter by lazy { ScoreEntryAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_leaderboard)
        leaderboardRecyclerView.layoutManager = LinearLayoutManager(this)
        leaderboardRecyclerView.adapter = scoreEntryAdapter
        leaderboardViewModel.getScoreEntries(remoteDbManager).observe(this, Observer { list ->
            if (list != null) {
                scoreEntryAdapter.items = list.sortedByDescending { it.score }
                scoreEntryAdapter.notifyDataSetChanged()
            }
        })
    }
}
