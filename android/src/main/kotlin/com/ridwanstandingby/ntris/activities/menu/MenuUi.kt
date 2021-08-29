package com.ridwanstandingby.ntris.activities.menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.ridwanstandingby.ntris.render.views.LayoutArrangement

@Composable
fun MenuActivityUi(vm: MenuViewModel, launcher: MenuActivity.Launcher) {
    MenuUi(
        polyominosLoaded = vm.polyominosLoaded,
        nickname = vm.nickname,
        layoutArrangement = vm.layoutArrangement,
        updateNickname = vm::updateNickname,
        updateLayoutArrangement = vm::updateLayoutArrangement,
        launchLeaderboard = launcher::launchLeaderboardActivity,
        launchTutorial = launcher::launchTutorialActivity,
        launchGame = launcher::launchGameActivity
    )
}

@Composable
fun MenuUi(
    polyominosLoaded: State<Boolean>,
    nickname: State<String>,
    layoutArrangement: State<LayoutArrangement>,
    updateNickname: (String) -> Unit,
    updateLayoutArrangement: (LayoutArrangement) -> Unit,
    launchLeaderboard: () -> Unit,
    launchTutorial: () -> Unit,
    launchGame: () -> Unit
) {

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

@Preview
@Composable
fun MenuUiPreview() {
    val nickname = remember { mutableStateOf("Nickname") }
    val layoutArrangement = remember { mutableStateOf(LayoutArrangement.DEFAULT) }
    MenuUi(
        polyominosLoaded = remember { mutableStateOf(false) },
        nickname = nickname,
        layoutArrangement = layoutArrangement,
        updateNickname = { nickname.value = it },
        updateLayoutArrangement = { layoutArrangement.value = it },
        launchLeaderboard = {},
        launchTutorial = {},
        launchGame = {}
    )
}
