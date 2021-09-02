package com.ridwanstandingby.ntris.ui.leaderboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.ridwanstandingby.ntris.R
import com.ridwanstandingby.ntris.domain.ScoreEntry
import com.ridwanstandingby.ntris.ui.theme.NTrisTheme
import java.lang.Thread.sleep
import java.util.*

@Composable
fun LeaderboardActivityUi(vm: LeaderboardViewModel) {
    LeaderboardUi(
        startingLeaderboardMode = LeaderboardMode.WEEKLY,
        weeklyScoreEntries = vm.weeklyScoreEntries.observeAsState(),
        allTimeScoreEntries = vm.allTimeScoreEntries.observeAsState()
    )
}

@Composable
fun LeaderboardUi(
    startingLeaderboardMode: LeaderboardMode,
    weeklyScoreEntries: State<List<ScoreEntry>?>,
    allTimeScoreEntries: State<List<ScoreEntry>?>,
) {
    NTrisTheme {
        Scaffold {
            Column {
                val leaderboardMode = remember { mutableStateOf(startingLeaderboardMode) }
                LeaderboardModeTabs(
                    leaderboardMode = leaderboardMode.value,
                    onSelectLeaderboardMode = { leaderboardMode.value = it }
                )
                HeaderRow()
                LeaderboardContent(
                    leaderboardMode = leaderboardMode.value,
                    allTimeScoreEntries = allTimeScoreEntries,
                    weeklyScoreEntries = weeklyScoreEntries
                )
            }
        }
    }
}

@Composable
fun LeaderboardModeTabs(
    leaderboardMode: LeaderboardMode,
    onSelectLeaderboardMode: (LeaderboardMode) -> Unit
) {
    TabRow(
        selectedTabIndex = leaderboardMode.ordinal,
        contentColor = MaterialTheme.colors.secondary
    ) {
        LeaderboardModeTab(
            tabMode = LeaderboardMode.WEEKLY,
            selectedMode = leaderboardMode,
            onSelectLeaderboardMode = onSelectLeaderboardMode
        )
        LeaderboardModeTab(
            tabMode = LeaderboardMode.ALL_TIME,
            selectedMode = leaderboardMode,
            onSelectLeaderboardMode = onSelectLeaderboardMode
        )
    }
}

@Composable
private fun LeaderboardModeTab(
    tabMode: LeaderboardMode,
    selectedMode: LeaderboardMode,
    onSelectLeaderboardMode: (LeaderboardMode) -> Unit
) {
    Tab(
        selected = selectedMode == tabMode,
        unselectedContentColor = Color.Gray,
        onClick = { onSelectLeaderboardMode(tabMode) }) {
        Text(
            text = stringResource(id = tabMode.toTabNameStringId()),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
    }
}

@Composable
fun HeaderRow() {
    ScoreRowSkeleton(
        numberText = stringResource(R.string.number_symbol),
        nameText = stringResource(R.string.name),
        linesText = stringResource(R.string.lines),
        scoreText = stringResource(R.string.score)
    )
}

@Composable
fun LeaderboardContent(
    leaderboardMode: LeaderboardMode,
    weeklyScoreEntries: State<List<ScoreEntry>?>,
    allTimeScoreEntries: State<List<ScoreEntry>?>
) {
    when (leaderboardMode) {
        LeaderboardMode.WEEKLY -> weeklyScoreEntries
        LeaderboardMode.ALL_TIME -> allTimeScoreEntries
    }.value?.let {
        if (it.isEmpty()) {
            CannotLoadScoresErrorMessage()
        } else {
            Scores(it)
        }
    } ?: LoadingLeaderboardProgressBar()
}

@Composable
fun LoadingLeaderboardProgressBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.61f)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .height(48.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun CannotLoadScoresErrorMessage() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.61f)
    ) {
        Text(
            text = stringResource(R.string.score_loading_connection_error_message),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun Scores(scoreEntries: List<ScoreEntry>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(-1f)
    ) {
        itemsIndexed(scoreEntries) { index, item ->
            ScoreRow(scoreEntry = item, number = index + 1)
        }
    }
}

@Composable
fun ScoreRow(scoreEntry: ScoreEntry, number: Int) {
    ScoreRowSkeleton(
        numberText = number.toString(),
        nameText = scoreEntry.name,
        linesText = scoreEntry.lines.toString(),
        scoreText = scoreEntry.score.toString()
    )
}

@Composable
fun ScoreRowSkeleton(
    numberText: String,
    nameText: String,
    linesText: String,
    scoreText: String
) {
    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
        ) {
            Text(
                text = numberText, textAlign = TextAlign.Right, fontSize = 12.sp,
                modifier = Modifier
                    .padding(top = 6.dp, bottom = 6.dp, start = 12.dp, end = 8.dp)
                    .weight(3f)
            )
            Text(
                text = nameText, textAlign = TextAlign.Left, fontSize = 12.sp,
                modifier = Modifier
                    .padding(top = 6.dp, bottom = 6.dp, start = 4.dp, end = 4.dp)
                    .weight(10f)
            )
            Text(
                text = linesText, textAlign = TextAlign.Right, fontSize = 12.sp,
                modifier = Modifier
                    .padding(top = 6.dp, bottom = 6.dp, start = 4.dp, end = 4.dp)
                    .weight(3f)
            )
            Text(
                text = scoreText, textAlign = TextAlign.Right, fontSize = 12.sp,
                modifier = Modifier
                    .padding(top = 6.dp, bottom = 6.dp, start = 4.dp, end = 12.dp)
                    .weight(5f)
            )
        }
        Divider()
    }
}

@Preview
@Composable
fun LeaderboardUiPreview() {
    LeaderboardUi(
        startingLeaderboardMode = LeaderboardMode.WEEKLY,
        weeklyScoreEntries = remember {
            mutableStateOf(
                listOf(
                    ScoreEntry(time = Date(), name = "Person A", score = 123450, lines = 2345),
                    ScoreEntry(time = Date(), name = "Person B", score = 310, lines = 31),
                    ScoreEntry(time = Date(), name = "Person with long name", score = 20, lines = 3)
                ) + List(200) {
                    ScoreEntry(time = Date(), name = "Person $it", score = it * 1L, lines = it * 1L)
                }
            )
        },
        allTimeScoreEntries = remember { mutableStateOf(emptyList()) }
    )
}
