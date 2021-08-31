package com.ridwanstandingby.ntris.ui.tutorial

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ridwanstandingby.ntris.R
import com.ridwanstandingby.ntris.ui.theme.NTrisTheme

@Composable
fun TutorialActivityUi() {
    TutorialUi()
}

@Composable
fun TutorialUi() {
    NTrisTheme {
        Scaffold {
            Image(
                painter = painterResource(R.drawable.how_to_play),
                contentDescription = stringResource(R.string.how_to_play),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview
@Composable
fun TutorialUiPreview() {
    TutorialUi()
}
