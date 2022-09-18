package com.example.appnomichallenge.ui.common.ext

import android.view.View

fun View?.setVisibility(
    isVisible: Boolean,
    setAsGone: Boolean = true
) {
    this?.let {
        visibility =
            when {
                isVisible -> View.VISIBLE
                setAsGone -> View.GONE
                else -> View.INVISIBLE
            }
    }
}
