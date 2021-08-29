package com.ridwanstandingby.ntris.activities.menu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.ridwanstandingby.ntris.activities.game.GameActivity
import com.ridwanstandingby.ntris.activities.tutorial.TutorialActivity
import com.ridwanstandingby.ntris.activities.leaderboard.LeaderboardActivity
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
