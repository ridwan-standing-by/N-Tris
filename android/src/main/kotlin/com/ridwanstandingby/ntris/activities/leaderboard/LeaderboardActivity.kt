package com.ridwanstandingby.ntris.activities.leaderboard

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TabHost
import com.ridwanstandingby.ntris.Application
import com.ridwanstandingby.ntris.R
import com.ridwanstandingby.ntris.domain.ScoreEntry
import kotlinx.android.synthetic.main.layout_leaderboard.*

class LeaderboardActivity : AppCompatActivity() {

    private val remoteDbManager by lazy { (application as Application).remoteDataManager }

    private lateinit var leaderboardViewModel: LeaderboardViewModel

    private lateinit var weeklyScoreEntryAdapter: ScoreEntryAdapter
    private lateinit var allTimeScoreEntryAdapter: ScoreEntryAdapter

    private lateinit var weeklyTab: TabHost.TabSpec
    private lateinit var allTimeTab: TabHost.TabSpec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_leaderboard)

        initTabs()

        initWeeklyRecyclerView()
        initAllTimeRecyclerView()

        initLeaderboardViewModel()
    }

    private fun initTabs() {
        leaderboardTabHost.setup()
        weeklyTab = leaderboardTabHost.newTabSpec(WEEKLY_TAB_TAG).setIndicator(getString(R.string.weekly_tab_title)).setContent(R.id.weeklyTabLayout)
        allTimeTab = leaderboardTabHost.newTabSpec(ALL_TIME_TAB_TAG).setIndicator(getString(R.string.all_time_tab_title)).setContent(R.id.allTimeTabLayout)
        leaderboardTabHost.addTab(weeklyTab)
        leaderboardTabHost.addTab(allTimeTab)
    }

    private fun initWeeklyRecyclerView() {
        weeklyScoreEntryAdapter = ScoreEntryAdapter(this)
        weeklyLeaderboardRecyclerView.layoutManager = LinearLayoutManager(this)
        weeklyLeaderboardRecyclerView.adapter = weeklyScoreEntryAdapter
    }

    private fun initAllTimeRecyclerView() {
        allTimeScoreEntryAdapter = ScoreEntryAdapter(this)
        allTimeLeaderboardRecyclerView.layoutManager = LinearLayoutManager(this)
        allTimeLeaderboardRecyclerView.adapter = allTimeScoreEntryAdapter
    }

    private fun initLeaderboardViewModel() {
        leaderboardViewModel = ViewModelProviders.of(this).get(LeaderboardViewModel::class.java)
        observeWeeklyScoreEntries()
        observeAllTimeScoreEntries()
    }

    private fun observeWeeklyScoreEntries() {
        leaderboardViewModel.getWeeklyScoreEntries(remoteDbManager).observe(this, Observer {
            if (it != null && it.isNotEmpty()) {
                handleWeeklyScoreEntriesLoaded(it)
            } else {
                handleWeeklyScoreEntriesEmpty()
            }
        })
    }

    private fun observeAllTimeScoreEntries() {
        leaderboardViewModel.getAllTimeScoreEntries(remoteDbManager).observe(this, Observer {
            if (it != null && it.isNotEmpty()) {
                handleAllTimeScoreEntriesLoaded(it)
            } else {
                handleAllTimeScoreEntriesLoadFailure()
            }
        })
    }

    private fun handleWeeklyScoreEntriesEmpty() {
        weeklyLoadingErrorText.visibility = View.VISIBLE
        weeklyLeaderboardRecyclerView.visibility = View.INVISIBLE
        weeklyLeaderboardLoadingProgressBar.visibility = View.INVISIBLE
    }

    private fun handleAllTimeScoreEntriesLoadFailure() {
        allTimeLoadingErrorText.visibility = View.VISIBLE
        allTimeLeaderboardRecyclerView.visibility = View.INVISIBLE
        allTimeLeaderboardLoadingProgressBar.visibility = View.INVISIBLE
    }

    private fun handleWeeklyScoreEntriesLoaded(list: List<ScoreEntry>) {
        weeklyScoreEntryAdapter.items = list
        weeklyScoreEntryAdapter.notifyDataSetChanged()
        weeklyLoadingErrorText.visibility = View.INVISIBLE
        weeklyLeaderboardRecyclerView.visibility = View.VISIBLE
        weeklyLeaderboardLoadingProgressBar.visibility = View.INVISIBLE
    }

    private fun handleAllTimeScoreEntriesLoaded(list: List<ScoreEntry>) {
        allTimeScoreEntryAdapter.items = list
        allTimeScoreEntryAdapter.notifyDataSetChanged()
        allTimeLoadingErrorText.visibility = View.INVISIBLE
        allTimeLeaderboardRecyclerView.visibility = View.VISIBLE
        allTimeLeaderboardLoadingProgressBar.visibility = View.INVISIBLE
    }

    companion object {
        private const val WEEKLY_TAB_TAG = "weekly"
        private const val ALL_TIME_TAB_TAG = "all_time"
    }
}
