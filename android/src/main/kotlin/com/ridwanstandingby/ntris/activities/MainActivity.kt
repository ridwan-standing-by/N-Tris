package com.ridwanstandingby.ntris.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.ridwanstandingby.ntris.Application
import com.ridwanstandingby.ntris.R
import com.ridwanstandingby.ntris.data.GameDataManager
import com.ridwanstandingby.ntris.render.views.LayoutArrangement
import kotlinx.android.synthetic.main.layout_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private lateinit var gameDataManager: GameDataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_main)
        gameDataManager = (application as Application).gameDataManager

        if (gameDataManager.polyominoBlueprintHolder != null) {
            handleFinishedLoading()
        } else {
            beginLoading()
        }

        initHandednessSwitch()
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

    private fun launchGameActivity() {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    private fun launchTutorialActivity() {
        val intent = Intent(this, TutorialActivity::class.java)
        startActivity(intent)
    }

    private fun initHandednessSwitch() {
        handednessSwitch.setCheckedImmediately(gameDataManager.layoutArrangement.toBoolean())
        handednessSwitch.setOnCheckedChangeListener { _, isChecked ->
            gameDataManager.layoutArrangement = isChecked.toLayoutArrangement()
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
