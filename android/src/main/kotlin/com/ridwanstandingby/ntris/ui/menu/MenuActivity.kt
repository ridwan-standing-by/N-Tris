package com.ridwanstandingby.ntris.ui.menu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.ridwanstandingby.ntris.ui.game.GameActivity
import com.ridwanstandingby.ntris.ui.tutorial.TutorialActivity
import com.ridwanstandingby.ntris.ui.leaderboard.LeaderboardActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuActivity : AppCompatActivity() {

    private val menuViewModel: MenuViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MenuActivityUi(menuViewModel, this.Launcher())
        }
        menuViewModel.start()
    }

    override fun onResume() {
        super.onResume()
        currentFocus?.clearFocus()
    }

    inner class Launcher {
        fun launchLeaderboardActivity() = startActivity<LeaderboardActivity>()
        fun launchTutorialActivity() = startActivity<TutorialActivity>()
        fun launchGameActivity() = startActivity<GameActivity>()
    }

    private inline fun <reified T : Activity> startActivity() {
        val intent = Intent(this, T::class.java)
        startActivity(intent)
    }
}
