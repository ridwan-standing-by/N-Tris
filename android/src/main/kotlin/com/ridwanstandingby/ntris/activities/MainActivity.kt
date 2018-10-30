package com.ridwanstandingby.ntris.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.ridwanstandingby.ntris.Application
import com.ridwanstandingby.ntris.R
import com.ridwanstandingby.ntris.activities.leaderboard.LeaderboardActivity
import com.ridwanstandingby.ntris.data.GameDataManager
import com.ridwanstandingby.ntris.data.SharedPreferencesManager
import com.ridwanstandingby.ntris.render.views.LayoutArrangement
import kotlinx.android.synthetic.main.layout_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private lateinit var gameDataManager: GameDataManager
    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_main)
        sharedPreferencesManager = (application as Application).sharedPreferencesManager
        gameDataManager = (application as Application).gameDataManager

        if (gameDataManager.polyominoBlueprintHolder != null) {
            handleFinishedLoading()
        } else {
            beginLoading()
        }

        initNicknameEditText()
        initHandednessSwitch()
        leaderboardButton.setOnClickListener { launchLeaderboardActivity() }
        howToPlayButton.setOnClickListener { launchTutorialActivity() }
    }

    private fun beginLoading() {
        playButton.visibility = View.INVISIBLE
        loadingProgressBar.visibility = View.VISIBLE
        loadingTextView.visibility = View.VISIBLE
        doAsync {
            gameDataManager.loadPolyominoBlueprints()
            uiThread {
                it.handleFinishedLoading()
            }
        }
    }

    private fun handleFinishedLoading() {
        loadingProgressBar.visibility = View.INVISIBLE
        loadingTextView.visibility = View.INVISIBLE
        playButton.visibility = View.VISIBLE
        playButton.setOnClickListener { launchGameActivity() }
    }

    private fun launchLeaderboardActivity() = startActivity<LeaderboardActivity>()
    private fun launchTutorialActivity() = startActivity<TutorialActivity>()
    private fun launchGameActivity() = startActivity<GameActivity>()

    private inline fun <reified T : Activity> startActivity() {
        val intent = Intent(this, T::class.java)
        startActivity(intent)
    }

    private fun initNicknameEditText() {
        nicknameEditText.setText(sharedPreferencesManager.nickname)
        nicknameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                sharedPreferencesManager.nickname = s.toString()
            }
        })
    }

    private fun initHandednessSwitch() {
        handednessSwitch.setCheckedImmediately(sharedPreferencesManager.layoutArrangement.toBoolean())
        handednessSwitch.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferencesManager.layoutArrangement = isChecked.toLayoutArrangement()
        }
    }

    private fun LayoutArrangement.toBoolean() =
            when (this) {
                LayoutArrangement.RIGHT_HANDED -> true
                LayoutArrangement.LEFT_HANDED -> false
            }

    private fun Boolean.toLayoutArrangement() =
            when (this) {
                true -> LayoutArrangement.RIGHT_HANDED
                false -> LayoutArrangement.LEFT_HANDED
            }
}
