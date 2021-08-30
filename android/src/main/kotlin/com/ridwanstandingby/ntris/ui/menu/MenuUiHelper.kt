package com.ridwanstandingby.ntris.ui.menu

import com.ridwanstandingby.ntris.render.views.LayoutArrangement

fun LayoutArrangement.toBoolean() =
    when (this) {
        LayoutArrangement.RIGHT_HANDED -> true
        LayoutArrangement.LEFT_HANDED -> false
    }

fun Boolean.toLayoutArrangement() =
    when (this) {
        true -> LayoutArrangement.RIGHT_HANDED
        false -> LayoutArrangement.LEFT_HANDED
    }
