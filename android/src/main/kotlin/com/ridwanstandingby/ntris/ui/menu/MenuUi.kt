@file:OptIn(ExperimentalAnimationApi::class)

package com.ridwanstandingby.ntris.ui.menu

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ridwanstandingby.ntris.R
import com.ridwanstandingby.ntris.render.views.LayoutArrangement
import com.ridwanstandingby.ntris.ui.theme.NTrisTheme

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
    NTrisTheme {
        Scaffold {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Logo()
                NicknameField(nickname, updateNickname)
                LayoutArrangementSwitch(layoutArrangement, updateLayoutArrangement)
                MenuButton(
                    stringResource(R.string.leaderboard),
                    launchLeaderboard,
                    menuButtonModifier
                )
                MenuButton(
                    stringResource(R.string.how_to_play),
                    launchTutorial,
                    menuButtonModifier
                )
                ProgressBarOrPlayButton(polyominosLoaded, launchGame)
            }
        }
    }
}

@Composable
fun Logo() {
    Image(
        painter = painterResource(R.drawable.logo_alien_encounters),
        contentDescription = null,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 96.dp, bottom = 32.dp)
    )
}

@Composable
fun NicknameField(
    nickname: State<String>,
    updateNickname: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = nickname.value,
        onValueChange = updateNickname,
        singleLine = true,
        label = {
            Text(
                text = stringResource(id = R.string.nickname),
                color = MaterialTheme.colors.secondary
            )
        },
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(0.61f)
            .height(64.dp)
    )
}

@Composable
fun LayoutArrangementSwitch(
    layoutArrangement: State<LayoutArrangement>,
    updateLayoutArrangement: (LayoutArrangement) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(0.8f)
            .height(32.dp)
    ) {
        Box(modifier = Modifier.weight(0.4f, fill = true)) {
            this@Row.AnimatedVisibility(
                visible = !layoutArrangement.value.toBoolean(),
                enter = expandHorizontally(Alignment.End),
                exit = shrinkHorizontally(Alignment.End),
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Text(stringResource(R.string.left_handed_switch_label))
            }
        }
        Switch(
            checked = layoutArrangement.value.toBoolean(),
            onCheckedChange = { updateLayoutArrangement(it.toLayoutArrangement()) },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colors.secondary,
                checkedTrackColor = MaterialTheme.colors.primary,
                uncheckedThumbColor = MaterialTheme.colors.secondary,
                uncheckedTrackColor = MaterialTheme.colors.primary
            ),
            modifier = Modifier.weight(0.2f, fill = true)
        )
        Box(modifier = Modifier.weight(0.4f, fill = true)) {
            this@Row.AnimatedVisibility(
                visible = layoutArrangement.value.toBoolean(),
                enter = expandHorizontally(Alignment.Start),
                exit = shrinkHorizontally(Alignment.Start),
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Text(stringResource(R.string.right_handed_switch_label))
            }
        }
    }
}

val menuButtonModifier = Modifier
    .padding(16.dp)
    .fillMaxWidth(0.61f)
    .height(48.dp)

@Composable
fun ProgressBarOrPlayButton(
    polyominosLoaded: State<Boolean>,
    launchGame: () -> Unit
) {
    Box(modifier = menuButtonModifier) {
        if (!polyominosLoaded.value) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.Center)
            )
        }
        AnimatedVisibility(
            visible = polyominosLoaded.value,
            enter = expandHorizontally(Alignment.CenterHorizontally),
            modifier = Modifier.align(Alignment.Center)
        ) {
            MenuButton(stringResource(R.string.play), launchGame, modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun MenuButton(
    buttonText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick, modifier = modifier
    ) {
        Text(buttonText)
    }
}

@Preview
@Composable
fun MenuUiPreview() {
    val nickname = remember { mutableStateOf("My nickname") }
    val layoutArrangement = remember { mutableStateOf(LayoutArrangement.DEFAULT) }
    MenuUi(
        polyominosLoaded = remember { mutableStateOf(true) },
        nickname = nickname,
        layoutArrangement = layoutArrangement,
        updateNickname = { nickname.value = it },
        updateLayoutArrangement = { layoutArrangement.value = it },
        launchLeaderboard = {},
        launchTutorial = {},
        launchGame = {}
    )
}
