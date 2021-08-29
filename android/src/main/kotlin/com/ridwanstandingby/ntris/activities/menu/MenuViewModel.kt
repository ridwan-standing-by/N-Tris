package com.ridwanstandingby.ntris.activities.menu

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ridwanstandingby.ntris.data.GameDataManager
import com.ridwanstandingby.ntris.data.local.SharedPreferencesManager
import com.ridwanstandingby.ntris.render.views.LayoutArrangement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuViewModel(
    private val gameDataManager: GameDataManager,
    private val sharedPreferencesManager: SharedPreferencesManager
) : ViewModel() {

    private val _polyominosLoaded: MutableState<Boolean> =
        mutableStateOf(false)
    private val _nickname: MutableState<String> =
        mutableStateOf(sharedPreferencesManager.nickname)
    private val _layoutArrangement: MutableState<LayoutArrangement> =
        mutableStateOf(sharedPreferencesManager.layoutArrangement)

    val polyominosLoaded: State<Boolean> = _polyominosLoaded
    val nickname: State<String> = _nickname
    val layoutArrangement: State<LayoutArrangement> = _layoutArrangement

    fun start() {
        viewModelScope.launch(Dispatchers.IO) {
            gameDataManager.loadPolyominoBlueprintsIfNecessary()
            _polyominosLoaded.value = true
        }
    }

    fun updateNickname(nickname: String) {
        sharedPreferencesManager.nickname = nickname
        _nickname.value = nickname
    }

    fun updateLayoutArrangement(layoutArrangement: LayoutArrangement) {
        sharedPreferencesManager.layoutArrangement = layoutArrangement
        _layoutArrangement.value = layoutArrangement
    }
}
